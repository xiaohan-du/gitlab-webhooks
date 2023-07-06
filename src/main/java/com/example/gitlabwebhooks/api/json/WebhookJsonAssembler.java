package com.example.gitlabwebhooks.api.json;

import com.example.gitlabwebhooks.service.WebhookDto;

import java.util.List;
import java.util.stream.Collectors;

public class WebhookJsonAssembler {
    public static WebhookJson toWebhookJson(WebhookDto webhookDto) {
        return WebhookJson
                .of()
                .object_kind(webhookDto.getObject_kind())
                .event_name(webhookDto.getEvent_name())
                .before_hash(webhookDto.getBefore_hash())
                .after_hash(webhookDto.getAfter_hash())
                .ref(webhookDto.getRef())
                .checkout_sha(webhookDto.getCheckout_sha())
                .user_id(webhookDto.getUser_id())
                .user_username(webhookDto.getUser_username())
                .project_id(webhookDto.getProject_id())
                .total_commits_count(webhookDto.getTotal_commits_count())
                .build();
    }

    public static List<WebhookJson> toWebhookJsonList(List<WebhookDto> webhookDtoList) {
        return webhookDtoList
                .stream()
                .map(w -> WebhookJsonAssembler.toWebhookJson(w))
                .collect(Collectors.toList());
    }
}
