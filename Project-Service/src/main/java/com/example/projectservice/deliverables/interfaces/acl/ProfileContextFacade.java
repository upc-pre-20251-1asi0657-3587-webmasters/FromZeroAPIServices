package com.example.projectservice.deliverables.interfaces.acl;


public interface ProfileContextFacade {
    Long getDeveloperByUserId(Long userId);
    Long getEnterpriseByUserId(Long userId);
    void updateDeveloperCompletedProjects(Long developerId);
}
