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

        Optional<Project> projectOptional = Optional.ofNullable(aWebhook.getProject());
        projectOptional.ifPresent(projectRepository::saveProjectData);
    }

    @Override
    public List<Webhook> getWebhookData() {
        List<Webhook> WebhookData = new ArrayList<>();
        webhookJdbcRepo.findAll().forEach(WebhookData::add);
        return WebhookData;
    }
}
