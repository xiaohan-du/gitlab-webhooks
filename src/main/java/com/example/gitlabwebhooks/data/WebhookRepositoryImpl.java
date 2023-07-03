package com.example.gitlabwebhooks.data;

import com.example.gitlabwebhooks.domain.Webhook;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class WebhookRepositoryImpl implements WebhookRepository {
    private final JdbcTemplate webhookJdbcTemplate;
    public WebhookRepositoryImpl(JdbcTemplate aWebhookJdbcTemplate) {
        this.webhookJdbcTemplate = aWebhookJdbcTemplate;
    }

    @Override
    public void saveWebhookData(Webhook aWebhook) {
        String saveWebhookInfoSQL = "INSERT INTO webhook (object_kind, event_name, before_hash, after_hash, ref, checkout_sha, user_id, user_username, project_id, total_commits_count) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
                aWebhook.getTotal_commits_count()
        );
    }
}
