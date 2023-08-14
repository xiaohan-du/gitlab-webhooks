package com.example.gitlabwebhooks.service;

import com.example.gitlabwebhooks.domain.Project;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class WebhookDto {
    private Integer id;
    private String object_kind;
    private String time_stamp;
    private Project project;
}
