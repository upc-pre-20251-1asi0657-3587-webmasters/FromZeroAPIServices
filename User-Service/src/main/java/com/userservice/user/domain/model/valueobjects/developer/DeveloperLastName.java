package com.userservice.user.domain.model.valueobjects.developer;

import jakarta.persistence.Embeddable;

@Embeddable
public record DeveloperLastName(String developerLastName) {
    public DeveloperLastName() { this(null); }

    public DeveloperLastName {
        if (developerLastName == null || developerLastName.isBlank()) {
            throw new IllegalArgumentException("Developer last name cannot be null");
        }
    }
}
