package com.example.projectservice.projects.interfaces.acl;

public interface ProfileContextFacade {
    Long getDeveloperByUserId(Long userId);
    Long getEnterpriseByUserId(Long userId);
    void updateDeveloperCompletedProjects(Long developerId);
}
