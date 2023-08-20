package com.example.gitlabwebhooks.data;

import com.example.gitlabwebhooks.domain.ObjectAttributes;
import com.example.gitlabwebhooks.domain.Project;
import com.example.gitlabwebhooks.domain.Webhook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class WebhookRepositoryImpl implements WebhookRepository {
    private final JdbcTemplate webhookJdbcTemplate;
    private final WebhookRepositorySpringDataJdbc webhookJdbcRepo;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private ObjectAttributesRepository objectAttributesRepository;
    public WebhookRepositoryImpl(JdbcTemplate aWebhookJdbcTemplate, WebhookRepositorySpringDataJdbc aWebhookRepositorySpringDataJdbc) {
        this.webhookJdbcRepo = aWebhookRepositorySpringDataJdbc;
        this.webhookJdbcTemplate = aWebhookJdbcTemplate;
    }


    @Override
    @Transactional
    public void saveWebhookData(Webhook aWebhook) {
        String saveWebhookInfoSQL = "INSERT INTO webhook (object_kind, time_stamp) VALUES (?, ?)";
        webhookJdbcTemplate.update(
                saveWebhookInfoSQL,
                aWebhook.getObject_kind(),
                aWebhook.getTime_stamp()
        );
        Integer lastWebhookId = getLatestWebhookId();
        Optional<Project> projectOptional = Optional.ofNullable(aWebhook.getProject());
        projectOptional.ifPresent(project -> projectRepository.saveProjectData(project, lastWebhookId));
        Optional<ObjectAttributes> objectAttributesOptional = Optional.ofNullable(aWebhook.getObjectAttributes());
        objectAttributesOptional.ifPresent(objectAttributes -> objectAttributesRepository.saveObjectAttributesData(objectAttributes, lastWebhookId));
    }

    private Integer getLatestWebhookId() {
        String query = "SELECT * FROM webhook";

        List<Webhook> webhookData = webhookJdbcTemplate.query(query, (rs, rowNum) -> {
            Webhook webhook = new Webhook(
                    rs.getInt("id"),
                    rs.getString("object_kind"),
                    rs.getString("time_stamp"),
                    null,
                    null
            );
            return webhook;
        });
        if (!webhookData.isEmpty()) {
            Webhook lastWebhook = webhookData.get(webhookData.size() - 1);
            int lastWebhookId = lastWebhook.getId();
            return lastWebhookId;
        }
        return null;
    }

    @Override
    public List<Webhook> getWebhookData() {
        String query = "SELECT w.*, p.*, oa.* FROM webhook w LEFT JOIN project p ON w.id = p.webhookId LEFT JOIN object_attributes oa ON w.id = oa.webhookId";
        List<Webhook> webhookData = webhookJdbcTemplate.query(query, (rs, rowNum) -> {
            ObjectAttributes objectAttributes = null;
            if (!rs.wasNull()) {
                objectAttributes = new ObjectAttributes(
                        rs.getInt("id"),
                        rs.getString("created_at"),
                        rs.getString("updated_at"),
                        rs.getString("description"),
                        rs.getString("url"),
                        rs.getInt("updated_by_id"),
                        rs.getInt("author_id"),
                        rs.getString("resolved_by_push")
                );
            }
            Webhook webhook = new Webhook(
                    rs.getInt("id"),
                    rs.getString("object_kind"),
                    rs.getString("time_stamp"),
                    new Project(
                            rs.getInt("id"),
                            rs.getString("homepage"),
                            rs.getString("default_branch"),
                            rs.getString("name"),
                            rs.getString("ref"),
                            rs.getString("description"),
                            rs.getString("web_url"),
                            rs.getString("git_ssh_url"),
                            rs.getString("git_http_url"),
                            rs.getInt("visibility_level"),
                            rs.getString("url"),
                            rs.getString("ssh_url"),
                            rs.getString("http_url")
                    ),
                    objectAttributes
            );
            return webhook;
        });
        return webhookData;
    }
}
