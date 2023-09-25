package com.example.gitlabwebhooks.service;

import com.example.gitlabwebhooks.domain.MergeRequestObjectAttributes;
import com.example.gitlabwebhooks.domain.Project;
import com.example.gitlabwebhooks.domain.Repository;
import com.example.gitlabwebhooks.domain.User;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class MergeRequestDto {
    private Integer merge_request_id;
    private MergeRequestObjectAttributes merge_request_object_attributes;
    private String changes;
    private Project project;
    private Repository repository;
    private User user;
    private String object_kind;
}
