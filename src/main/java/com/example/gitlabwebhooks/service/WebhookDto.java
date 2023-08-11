package com.example.gitlabwebhooks.service;

import com.example.gitlabwebhooks.domain.Project;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class WebhookDto {
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

    private Project project;
}
