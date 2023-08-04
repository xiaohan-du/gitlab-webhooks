package com.example.gitlabwebhooks.data;

import com.example.gitlabwebhooks.domain.Project;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProjectRepositoryImpl implements ProjectRepository {
    private final JdbcTemplate projectJdbcTemplate;
    private final ProjectRepositorySpringDataJdbc projectJdbcRepo;
    public ProjectRepositoryImpl(JdbcTemplate aProjectJdbcTemplate, ProjectRepositorySpringDataJdbc aProjectRepositorySpringDataJdbc) {
        this.projectJdbcRepo = aProjectRepositorySpringDataJdbc;
        this.projectJdbcTemplate = aProjectJdbcTemplate;
    }

    @Override
    public void saveProjectData(Project aProject) {
        String saveProjectInfoSQL = "INSERT INTO project (id, homepage, default_branch, name, ref, description, web_url, git_ssh_url, git_http_url, visibility_level, url, ssh_url, http_url) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        projectJdbcTemplate.update(
                saveProjectInfoSQL,
                aProject.getId(),
                aProject.getHomepage(),
                aProject.getDefault_branch(),
                aProject.getName(),
                aProject.getRef(),
                aProject.getDescription(),
                aProject.getWeb_url(),
                aProject.getGit_ssh_url(),
                aProject.getGit_http_url(),
                aProject.getVisibility_level(),
                aProject.getUrl(),
                aProject.getSsh_url(),
                aProject.getHttp_url()
        );
    }
}
