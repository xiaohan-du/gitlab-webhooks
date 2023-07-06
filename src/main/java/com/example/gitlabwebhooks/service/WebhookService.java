package com.example.gitlabwebhooks.service;

import com.example.gitlabwebhooks.service.message.WebhookRequest;
import com.example.gitlabwebhooks.service.message.WebhookResponse;

public interface WebhookService {
    void saveWebhookData(WebhookDto newWebhookData);
    WebhookResponse getWebhookData(WebhookRequest webhookRequest);
}
