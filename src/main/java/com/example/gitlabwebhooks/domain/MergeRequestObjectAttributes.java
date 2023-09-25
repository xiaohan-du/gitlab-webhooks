package com.example.gitlabwebhooks.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class MergeRequestObjectAttributes extends ObjectAttributes {
    private Boolean merge_when_pipeline_succeeds;
    private LastCommit last_commit;
    private String detailed_merge_status;
    private Integer iid;
    private String merge_user_id;
    private String milestone_id;
    private Source source;
    private MergeParams merge_params;
    private Target target;
}
