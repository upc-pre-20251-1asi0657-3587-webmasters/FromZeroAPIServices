package com.fromzero.candidatesservice.candidatesManagement.domain.model.events;

import lombok.Getter;
import lombok.Setter;

public class DeveloperAppliedEvent {
    private Long projectId;
    private String developerId;

    public DeveloperAppliedEvent() {

    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(String developerId) {
        this.developerId = developerId;
    }
}
