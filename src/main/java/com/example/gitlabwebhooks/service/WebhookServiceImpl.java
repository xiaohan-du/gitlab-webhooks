package com.example.gitlabwebhooks.service;

import com.example.gitlabwebhooks.data.WebhookRepository;
import com.example.gitlabwebhooks.domain.Webhook;
import com.example.gitlabwebhooks.service.message.WebhookRequest;
import com.example.gitlabwebhooks.service.message.WebhookResponse;
import org.springframework.stereotype.Service;

import java.util.List;

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
                webhookDto.getTime_stamp(),
                webhookDto.getProject(),
                webhookDto.getObjectAttributes()
        );
        webhookRepository.saveWebhookData(webhook);
    }

    @Override
    public WebhookResponse getWebhookData(WebhookRequest webhookRequest) {
        List<Webhook> webhookData = webhookRepository.getWebhookData();
        List<WebhookDto> webhookDtos = WebhookAssembler.toDto(webhookData);
        return WebhookResponse
                .of()
                .webhookRequest(webhookRequest)
                .webhookData(webhookDtos)
                .build();
    }
}
