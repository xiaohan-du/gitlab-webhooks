package com.example.gitlabwebhooks.data;

import com.example.gitlabwebhooks.domain.ObjectAttributes;

public interface ObjectAttributesRepository {
    void saveObjectAttributesData(ObjectAttributes newObjectAttributes, Integer webhookId);
}
