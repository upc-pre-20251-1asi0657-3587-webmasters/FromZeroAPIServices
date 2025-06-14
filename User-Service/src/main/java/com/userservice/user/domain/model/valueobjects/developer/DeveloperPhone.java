package com.userservice.user.domain.model.valueobjects.developer;

import jakarta.persistence.Embeddable;

@Embeddable
public record DeveloperPhone(String developerPhone) {
    public DeveloperPhone() { this("Not phone provided"); }

    public DeveloperPhone {
        if (developerPhone == null || developerPhone.isBlank()) {
            throw new IllegalArgumentException("Developer phone cannot be null");
        }
    }
}
