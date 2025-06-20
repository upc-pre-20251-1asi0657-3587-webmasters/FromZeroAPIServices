package com.userservice.user.domain.model.valueobjects.developer;

import jakarta.persistence.Embeddable;

@Embeddable
public record DeveloperCountry(String developerCountry) {
    public DeveloperCountry() { this("Not country provided"); }

    public DeveloperCountry {
        if (developerCountry == null || developerCountry.isBlank()) {
            throw new IllegalArgumentException("Developer country cannot be null");
        }
    }
}
