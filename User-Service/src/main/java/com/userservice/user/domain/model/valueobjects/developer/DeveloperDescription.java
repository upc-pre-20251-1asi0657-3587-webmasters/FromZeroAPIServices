package com.userservice.user.domain.model.valueobjects.developer;

import jakarta.persistence.Embeddable;

@Embeddable
public record DeveloperDescription(String developerDescription) {
    public DeveloperDescription() { this("Not description provided"); }

    public DeveloperDescription {
        if (developerDescription == null || developerDescription.isBlank()) {
            throw new IllegalArgumentException("Developer description cannot be null");
        }
    }
}
