package com.userservice.user.domain.model.valueobjects.developer;

import jakarta.persistence.Embeddable;

@Embeddable
public record DeveloperFirstName(String developerFirstName) {
    public DeveloperFirstName() { this("Not first name provided"); }

    public DeveloperFirstName {
        if (developerFirstName == null || developerFirstName.isBlank()) {
            throw new IllegalArgumentException("Developer first name cannot be null");
        }
    }
}
