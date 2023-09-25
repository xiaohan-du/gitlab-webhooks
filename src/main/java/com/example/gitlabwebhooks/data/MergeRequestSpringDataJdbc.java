package com.example.gitlabwebhooks.data;

import com.example.gitlabwebhooks.domain.MergeRequest;
import org.springframework.data.repository.CrudRepository;

public interface MergeRequestSpringDataJdbc extends CrudRepository<MergeRequest, Integer> {
}
