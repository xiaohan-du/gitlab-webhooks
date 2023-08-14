package com.example.gitlabwebhooks.api.json;

import com.example.gitlabwebhooks.service.WebhookDto;

import java.util.List;
import java.util.stream.Collectors;

public class WebhookJsonAssembler {
    public static WebhookJson toWebhookJson(WebhookDto webhookDto) {
        return WebhookJson
                .of()
                .object_kind(webhookDto.getObject_kind())
                .time_stamp(webhookDto.getTime_stamp())
                .project(webhookDto.getProject())
                .build();
    }

    public static List<WebhookJson> toWebhookJsonList(List<WebhookDto> webhookDtoList) {
        return webhookDtoList
                .stream()
                .map(w -> WebhookJsonAssembler.toWebhookJson(w))
                .collect(Collectors.toList());
    }
}
