package com.userservice.user.domain.model.valueobjects.developer;

import jakarta.persistence.Embeddable;

@Embeddable
public record DeveloperCompletedProjects(int developerCompletedProjects) {
    public DeveloperCompletedProjects() { this(0); }

    public DeveloperCompletedProjects {
        if (developerCompletedProjects < 0) {
            throw new IllegalArgumentException("Completed projects cannot be negative");
        }
    }
}
