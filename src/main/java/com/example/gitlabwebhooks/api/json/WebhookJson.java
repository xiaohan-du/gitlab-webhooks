package com.example.gitlabwebhooks.api.json;

import com.example.gitlabwebhooks.domain.Project;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@AllArgsConstructor
@Builder(builderMethodName = "of")
public class WebhookJson {
    private String object_kind;
    private String time_stamp;
    private Project project;
}
