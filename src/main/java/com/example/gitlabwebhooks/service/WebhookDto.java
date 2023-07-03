package com.example.gitlabwebhooks.service;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class WebhookDto {
    private static int nextId = 1;
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
    public WebhookDto(String objectKind, String eventName, String beforeHash, String afterHash,
                      String ref, String checkoutSha, int userId, String userUsername,
                      int projectId, int totalCommitsCount) {
        this.id = nextId++;
        this.object_kind = objectKind;
        this.event_name = eventName;
        this.before_hash = beforeHash;
        this.after_hash = afterHash;
        this.ref = ref;
        this.checkout_sha = checkoutSha;
        this.user_id = userId;
        this.user_username = userUsername;
        this.project_id = projectId;
        this.total_commits_count = totalCommitsCount;
    }
}
