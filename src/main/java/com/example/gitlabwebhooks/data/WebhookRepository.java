package com.example.gitlabwebhooks.data;

import com.example.gitlabwebhooks.domain.Webhook;

import java.util.List;

public interface WebhookRepository {
    void saveWebhookData(Webhook newWebhook);
    List<Webhook> getWebhookData();
}
