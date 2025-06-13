package com.fromzero.deliverableservice.deliverables.domain.model.commands;

public record CreateDefaultDeliverableCommand(String projectId,String projectType) {
    public CreateDefaultDeliverableCommand {
        if (projectId == null || projectId.isBlank()) {
            throw new IllegalArgumentException("Project ID cannot be null or blank");
        }
        if (projectType == null || projectType.isBlank()) {
            throw new IllegalArgumentException("Project type cannot be null or blank");
        }
    }
}
