package com.example.gitlabwebhooks.data;

import com.example.gitlabwebhooks.domain.ObjectAttributes;
import org.springframework.data.repository.CrudRepository;

public interface ObjectAttributesRepositorySpringDataJdbc extends CrudRepository<ObjectAttributes, Integer> {
}
