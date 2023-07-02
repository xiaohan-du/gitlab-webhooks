package com.example.gitlabwebhooks.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
public class Webhook {
    @Id
    private Integer id;
    private String object_kind;
    private String event_name;
    private String before;
    private String after;
    private String ref;
    private String checkout_sha;
    private Integer user_id;
    private String user_username;
    private Integer project_id;
    private Integer total_commits_count;
}
