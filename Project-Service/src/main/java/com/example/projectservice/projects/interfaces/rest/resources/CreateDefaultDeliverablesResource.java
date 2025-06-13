package com.example.projectservice.projects.interfaces.rest.resources;

public record CreateDefaultDeliverablesResource(String projectId,String projectType) {
    public CreateDefaultDeliverablesResource {
        if (projectId == null || projectId.isBlank()) {
            throw new IllegalArgumentException("Project ID cannot be null or blank");
        }
        if (projectType == null || projectType.isBlank()) {
            throw new IllegalArgumentException("Project type cannot be null or blank");
        }
    }
}
