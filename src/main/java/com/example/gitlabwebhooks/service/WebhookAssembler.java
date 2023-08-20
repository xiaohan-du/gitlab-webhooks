package com.example.gitlabwebhooks.service;

import com.example.gitlabwebhooks.domain.Webhook;

import java.util.List;
import java.util.stream.Collectors;

public class WebhookAssembler {
    public static List<WebhookDto> toDto(List<Webhook> listings) {
        return listings.stream().map(l -> toDto(l)).collect(Collectors.toList());
    }

    public static WebhookDto toDto(Webhook webhook) {
        return new WebhookDto(
                webhook.getId(),
                webhook.getObject_kind(),
                webhook.getTime_stamp(),
                webhook.getProject(),
                webhook.getObjectAttributes()
        );
    }
}
