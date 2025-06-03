package com.example.projectservice.deliverables.domain.model.commands;

public record DeleteDeliverableCommand(Long projectId, Long deliverableId) {
    public DeleteDeliverableCommand {
        if (projectId == null || deliverableId == null) {
            throw new IllegalArgumentException("Project ID and Deliverable ID cannot be null");
        }
    }
}
