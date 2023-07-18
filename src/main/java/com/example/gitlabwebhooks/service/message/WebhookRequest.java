package com.example.gitlabwebhooks.service.message;

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
    private String event_name;
    private String before_hash;
    private String after_hash;
    private String ref;
    private String checkout_sha;
    private Integer user_id;
    private String user_username;
    private Integer project_id;
    private Integer total_commits_count;
    private String time_stamp;
}
