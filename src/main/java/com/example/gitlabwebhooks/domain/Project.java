package com.example.gitlabwebhooks.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Project {
    //    The id generated by Gitlab, can be duplicated, should not be a Primary key
    private Integer project_id;
    private String homepage;
    private String default_branch;
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
