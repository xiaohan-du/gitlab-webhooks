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
    private String time_stamp;
    private Project project;
    private ObjectAttributes objectAttributes;
}
