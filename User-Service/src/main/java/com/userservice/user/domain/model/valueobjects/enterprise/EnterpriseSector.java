package com.userservice.user.domain.model.valueobjects.enterprise;

import jakarta.persistence.Embeddable;

@Embeddable
public record EnterpriseSector(String enterpriseSector) {
    public EnterpriseSector() { this("No sector provided"); }

    public EnterpriseSector {
        if (enterpriseSector == null || enterpriseSector.isBlank()) {
            throw new IllegalArgumentException("Enterprise sector cannot be null");
        }
    }
}
