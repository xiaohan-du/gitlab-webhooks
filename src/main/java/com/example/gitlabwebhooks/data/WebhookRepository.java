package com.example.gitlabwebhooks.data;

import com.example.gitlabwebhooks.domain.Webhook;

public interface WebhookRepository {
    void saveWebhookData(Webhook newWebhook);
}
