package com.fromzero.candidatesservice.candidatesManagement.domain.model.events;


import lombok.Getter;
import lombok.Setter;

public class DeveloperSelectedEvent {
    private Long projectId;
    private String developerId;
    private String candidateId;

    public DeveloperSelectedEvent() {

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

    public String getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
    }
}
