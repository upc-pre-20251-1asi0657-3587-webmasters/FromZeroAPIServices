package com.fromzero.candidatesservice.candidatesManagement.domain.model.commands;

import java.util.UUID;

public record ApplyToProjectCommand(UUID developerId, UUID projectId, String firstName, String lastName, String description) {

    public ApplyToProjectCommand {
        if (developerId == null){
            throw new IllegalArgumentException("Developer ID cannot be null");
        }
        if (projectId == null){
            throw new IllegalArgumentException("Project ID cannot be null");
        }
        if (firstName == null || firstName.isBlank()){
            throw new IllegalArgumentException("First name cannot be null or blank");
        }
        if (lastName == null || lastName.isBlank()){
            throw new IllegalArgumentException("Last name cannot be null or blank");
        }
        if (description == null){
            throw new IllegalArgumentException("Description cannot be null");
        }
    }
}
