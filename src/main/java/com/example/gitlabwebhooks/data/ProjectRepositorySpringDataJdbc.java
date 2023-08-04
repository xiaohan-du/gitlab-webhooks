package com.example.gitlabwebhooks.data;

import com.example.gitlabwebhooks.domain.Project;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepositorySpringDataJdbc extends CrudRepository<Project, Integer> {
}
