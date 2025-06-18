package com.userservice.user.domain.model.valueobjects.developer;

import jakarta.persistence.Embeddable;

@Embeddable
public record DeveloperEmail(String developerEmail) {
    public DeveloperEmail() { this("Not email provided"); };

    public DeveloperEmail {
        if (developerEmail == null || developerEmail.isBlank()) {
            throw new IllegalArgumentException("Developer email cannot be null");
        }
    }
}
