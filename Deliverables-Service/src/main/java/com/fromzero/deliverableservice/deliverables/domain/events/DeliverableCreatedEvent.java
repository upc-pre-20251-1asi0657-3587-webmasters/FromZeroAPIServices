package com.fromzero.deliverableservice.deliverables.domain.events;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class DeliverableCreatedEvent {
    private Long projectId;
    private String deliverableName;
    private LocalDateTime deadline;

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getDeliverableName() {
        return deliverableName;
    }

    public void setDeliverableName(String deliverableName) {
        this.deliverableName = deliverableName;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }
}

