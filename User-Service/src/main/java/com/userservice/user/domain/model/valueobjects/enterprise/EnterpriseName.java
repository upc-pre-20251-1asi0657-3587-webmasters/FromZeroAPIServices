package com.userservice.user.domain.model.valueobjects.enterprise;

import jakarta.persistence.Embeddable;

@Embeddable
public record EnterpriseName(String enterpriseName) {
    public EnterpriseName() { this("No name provided"); }

    public EnterpriseName {
        if (enterpriseName == null || enterpriseName.isBlank()) {
            throw new IllegalArgumentException("Enterprise name cannot be null");
        }
    }
}
