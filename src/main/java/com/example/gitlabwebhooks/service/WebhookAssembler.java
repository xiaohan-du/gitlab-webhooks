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
                webhook.getObject_kind(),
                webhook.getEvent_name(),
                webhook.getBefore_hash(),
                webhook.getAfter_hash(),
                webhook.getRef(),
                webhook.getCheckout_sha(),
                webhook.getUser_id(),
                webhook.getUser_username(),
                webhook.getProject_id(),
                webhook.getTotal_commits_count(),
                webhook.getTime_stamp(),
                webhook.getProject()
        );
    }
}
