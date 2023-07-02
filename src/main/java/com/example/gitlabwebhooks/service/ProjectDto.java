package com.example.gitlabwebhooks.service;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class ProjectDto {
    private Integer id;
    private String name;
    private String ref;
    private String description;
    private String web_url;
    private String git_ssh_url;
    private String git_http_url;
    private Integer visibility_level;
    private String url;
    private String ssh_url;
    private String http_url;
}
