package com.userservice.user.domain.model.valueobjects.developer;

import jakarta.persistence.Embeddable;

@Embeddable
public record DeveloperSpecialties(String developerSpecialties) {
    public DeveloperSpecialties() { this(null); }

    public DeveloperSpecialties {
        if (developerSpecialties == null || developerSpecialties.isBlank()) {
            throw new IllegalArgumentException("Developer specialties cannot be null");
        }
    }
}
