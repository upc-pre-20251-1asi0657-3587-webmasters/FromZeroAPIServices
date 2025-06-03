package com.example.projectservice.projects.domain.model.commands;

public record DeleteProjectCommand (Long projectId) {
    public DeleteProjectCommand {
        if (projectId == null) {
            throw new IllegalArgumentException("Project ID cannot be null");
        }
    }
}
