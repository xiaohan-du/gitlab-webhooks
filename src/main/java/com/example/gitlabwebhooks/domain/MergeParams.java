package com.example.gitlabwebhooks.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MergeParams {
    private Boolean should_remove_source_branch;
    private Integer force_remove_source_branch;
}
