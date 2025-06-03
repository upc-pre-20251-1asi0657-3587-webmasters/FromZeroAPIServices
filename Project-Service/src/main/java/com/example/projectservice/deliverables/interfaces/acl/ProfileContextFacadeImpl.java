package com.example.projectservice.deliverables.interfaces.acl;

import org.springframework.stereotype.Service;

@Service("profileContextFacadeForDeliverables")
public class ProfileContextFacadeImpl implements ProfileContextFacade {
    @Override
    public Long getDeveloperByUserId(Long userId) {
        return 0L;
    }

    @Override
    public Long getEnterpriseByUserId(Long userId) {
        return 0L;
    }

    @Override
    public void updateDeveloperCompletedProjects(Long developerId) {

    }
}
