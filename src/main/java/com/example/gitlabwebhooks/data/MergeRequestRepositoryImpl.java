package com.example.gitlabwebhooks.data;

import com.example.gitlabwebhooks.domain.MergeRequest;
import org.springframework.jdbc.core.JdbcTemplate;

public class MergeRequestRepositoryImpl implements MergeRequestRepository{
    private final JdbcTemplate mergeRequestJdbcTemplate;
    private final MergeRequestSpringDataJdbc mergeRequestJdbcRepo;
    public MergeRequestRepositoryImpl(JdbcTemplate aMergeRequestJdbcTemplate, MergeRequestSpringDataJdbc aMergeRequestSpringDataJdbc) {
        this.mergeRequestJdbcRepo = aMergeRequestSpringDataJdbc;
        this.mergeRequestJdbcTemplate = aMergeRequestJdbcTemplate;
    }

    @Override
    public void saveMergeRequestData(MergeRequest aMergeRequest, Integer webhook_id) {
    }
}
