package com.example.gitlabwebhooks.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ObjectAttributes {
    //    The id generated by Gitlab, can be duplicated, should not be a Primary key
    private Integer object_attributes_id;
    private String created_at;
    private String updated_at;
    private String description;
    private String url;
    private Integer updated_by_id;
    private Integer author_id;
    private String resolved_by_push;
}
