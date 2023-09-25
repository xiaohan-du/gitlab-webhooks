package com.example.gitlabwebhooks.data;

import com.example.gitlabwebhooks.domain.MergeRequest;

public interface MergeRequestRepository {
    void saveMergeRequestData(MergeRequest aMergeRequest, Integer webhook_id);
}
