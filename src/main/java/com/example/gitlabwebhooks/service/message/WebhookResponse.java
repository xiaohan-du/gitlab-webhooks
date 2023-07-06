package com.example.gitlabwebhooks.service.message;

import com.example.gitlabwebhooks.service.WebhookDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@AllArgsConstructor
@Builder(builderMethodName = "of")
public class WebhookResponse {
    private final WebhookRequest webhookRequest;
    private List<WebhookDto> webhookData;
}
