package com.example.gitlabwebhooks.data;

import com.example.gitlabwebhooks.domain.Project;

public interface ProjectRepository {
    void saveProjectData(Project newProject);
}
