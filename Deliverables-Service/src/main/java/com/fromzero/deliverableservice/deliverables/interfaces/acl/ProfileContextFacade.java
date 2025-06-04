package com.fromzero.deliverableservice.deliverables.interfaces.acl;


public interface ProfileContextFacade {
    Long getDeveloperByUserId(Long userId);
    Long getEnterpriseByUserId(Long userId);
    void updateDeveloperCompletedProjects(Long developerId);
}
