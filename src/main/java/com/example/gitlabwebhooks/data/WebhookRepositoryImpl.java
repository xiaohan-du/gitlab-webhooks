package com.example.gitlabwebhooks.data;

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
    public WebhookRepositoryImpl(JdbcTemplate aWebhookJdbcTemplate, WebhookRepositorySpringDataJdbc aWebhookRepositorySpringDataJdbc) {
        this.webhookJdbcRepo = aWebhookRepositorySpringDataJdbc;
        this.webhookJdbcTemplate = aWebhookJdbcTemplate;
    }


    @Override
    @Transactional
    public void saveWebhookData(Webhook aWebhook) {
        String saveWebhookInfoSQL = "INSERT INTO webhook (object_kind, event_name, before_hash, after_hash, ref, checkout_sha, user_id, user_username, project_id, total_commits_count, time_stamp) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        webhookJdbcTemplate.update(
                saveWebhookInfoSQL,
                aWebhook.getObject_kind(),
                aWebhook.getEvent_name(),
                aWebhook.getBefore_hash(),
                aWebhook.getAfter_hash(),
                aWebhook.getRef(),
                aWebhook.getCheckout_sha(),
                aWebhook.getUser_id(),
                aWebhook.getUser_username(),
                aWebhook.getProject_id(),
                aWebhook.getTotal_commits_count(),
                aWebhook.getTime_stamp()
        );
        Integer lastWebhookId = getLatestWebhookId();
        Optional<Project> projectOptional = Optional.ofNullable(aWebhook.getProject());
        projectOptional.ifPresent(project -> projectRepository.saveProjectData(project, lastWebhookId));
    }

    private Integer getLatestWebhookId() {
        String query = "SELECT * FROM webhook";

        List<Webhook> webhookData = webhookJdbcTemplate.query(query, (rs, rowNum) -> {
            Webhook webhook = new Webhook(
                    rs.getInt("id"),
                    rs.getString("object_kind"),
                    rs.getString("event_name"),
                    rs.getString("before_hash"),
                    rs.getString("after_hash"),
                    rs.getString("ref"),
                    rs.getString("checkout_sha"),
                    rs.getInt("user_id"),
                    rs.getString("user_username"),
                    rs.getInt("project_id"),
                    rs.getInt("total_commits_count"),
                    rs.getString("time_stamp"),
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
        String query = "SELECT w.*, p.* FROM webhook w LEFT JOIN project p ON w.id = p.webhookId";
        List<Webhook> webhookData = webhookJdbcTemplate.query(query, (rs, rowNum) -> {
            Webhook webhook = new Webhook(
                    rs.getInt("id"),
                    rs.getString("object_kind"),
                    rs.getString("event_name"),
                    rs.getString("before_hash"),
                    rs.getString("after_hash"),
                    rs.getString("ref"),
                    rs.getString("checkout_sha"),
                    rs.getInt("user_id"),
                    rs.getString("user_username"),
                    rs.getInt("project_id"),
                    rs.getInt("total_commits_count"),
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
                    )
            );
            return webhook;
        });
        return webhookData;
    }
}
