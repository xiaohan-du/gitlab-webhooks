package com.example.gitlabwebhooks.data;

import com.example.gitlabwebhooks.domain.Webhook;
import org.springframework.data.repository.CrudRepository;

public interface WebhookRepositorySpringDataJdbc extends CrudRepository<Webhook, Integer> {
}
