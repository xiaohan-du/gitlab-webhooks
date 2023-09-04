package com.example.gitlabwebhooks.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LastCommit {
    //    The id generated by Gitlab, can be duplicated, should not be a Primary key
    private String last_commit_id;
    private Author author;
    private String message;
    private String title;
    private String url;
    private String timestamp;
}
