package com.example.gitlabwebhooks.service;

import com.example.gitlabwebhooks.data.WebhookRepository;
import com.example.gitlabwebhooks.domain.Webhook;
import org.springframework.stereotype.Service;

@Service
public class WebhookServiceImpl implements WebhookService {
    private final WebhookRepository webhookRepository;

    public WebhookServiceImpl(WebhookRepository webhookRepository) {
        this.webhookRepository = webhookRepository;
    }

    @Override
    public void saveWebhookData(WebhookDto webhookDto) {
        Webhook webhook = new Webhook(
                webhookDto.getId(),
                webhookDto.getObject_kind(),
                webhookDto.getEvent_name(),
                webhookDto.getBefore_hash(),
                webhookDto.getAfter_hash(),
                webhookDto.getRef(),
                webhookDto.getCheckout_sha(),
                webhookDto.getUser_id(),
                webhookDto.getUser_username(),
                webhookDto.getProject_id(),
                webhookDto.getTotal_commits_count()
        );
        webhookRepository.saveWebhookData(webhook);
    }
}
