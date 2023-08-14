package com.example.gitlabwebhooks.service.message;

import com.example.gitlabwebhooks.domain.Project;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import org.springframework.data.annotation.Id;

@Value
@AllArgsConstructor
@Builder(builderMethodName = "of")
public class WebhookRequest {
    @Id
    private Integer id;
    private String object_kind;
    private String time_stamp;
    private Project project;
}
