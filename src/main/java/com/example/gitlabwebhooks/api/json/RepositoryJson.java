package com.example.gitlabwebhooks.api.json;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@AllArgsConstructor
@Builder(builderMethodName = "of")
public class RepositoryJson {
    private String name;
    private String url;
    private String description;
    private String homepage;
    private String git_http_url;
    private String git_ssh_url;
    private Integer visibility_level;
}
