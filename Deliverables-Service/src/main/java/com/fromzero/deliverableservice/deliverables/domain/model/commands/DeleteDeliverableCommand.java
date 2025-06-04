package com.fromzero.deliverableservice.deliverables.domain.model.commands;

public record DeleteDeliverableCommand(String projectId, Long deliverableId) {
    public DeleteDeliverableCommand {
        if (projectId == null || deliverableId == null) {
            throw new IllegalArgumentException("Project ID and Deliverable ID cannot be null");
        }
    }
}
