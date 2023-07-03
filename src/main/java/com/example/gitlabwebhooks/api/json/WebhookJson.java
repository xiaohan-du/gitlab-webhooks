package com.example.gitlabwebhooks.api.json;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@AllArgsConstructor
@Builder(builderMethodName = "of")
public class WebhookJson {
    private String object_kind;
    private String event_name;
    private String before_hash;
    private String after_hash;
    private String ref;
    private String checkout_sha;
    private int user_id;
    private String user_username;
    private int project_id;
    private int total_commits_count;

}
