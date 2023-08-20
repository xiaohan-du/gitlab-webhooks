package com.example.gitlabwebhooks.data;

import com.example.gitlabwebhooks.domain.ObjectAttributes;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ObjectAttributesRepositoryImpl implements ObjectAttributesRepository {
    private final JdbcTemplate objectAttributesJdbcTemplate;
    private final ObjectAttributesRepositorySpringDataJdbc objectAttributesJdbcRepo;
    public ObjectAttributesRepositoryImpl(JdbcTemplate aObjectAttributesJdbcTemplate, ObjectAttributesRepositorySpringDataJdbc aObjectAttributesRepositorySPringDataJdbc) {
        this.objectAttributesJdbcRepo = aObjectAttributesRepositorySPringDataJdbc;
        this.objectAttributesJdbcTemplate = aObjectAttributesJdbcTemplate;
    }

    @Override
    public void saveObjectAttributesData(ObjectAttributes aObjectAttributes, Integer webhookId) {
        System.out.println("11111111111" + aObjectAttributes.getUrl());
        String saveObjectAttributesInfoSQL = "INSERT INTO object_attributes (webhookId, id, created_at, updated_at, description, url, updated_by_id, author_id, resolved_by_push) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        objectAttributesJdbcTemplate.update(
                saveObjectAttributesInfoSQL,
                webhookId,
                aObjectAttributes.getId(),
                aObjectAttributes.getCreated_at(),
                aObjectAttributes.getUpdated_at(),
                aObjectAttributes.getDescription(),
                aObjectAttributes.getUrl(),
                aObjectAttributes.getUpdated_by_id(),
                aObjectAttributes.getAuthor_id(),
                aObjectAttributes.getResolved_by_push()
        );
    }
}
