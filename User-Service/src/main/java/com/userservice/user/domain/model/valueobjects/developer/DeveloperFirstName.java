package com.userservice.user.domain.model.valueobjects.developer;

public record DeveloperFirstName(String developerFirstName) {
    public DeveloperFirstName() { this(null); }

    public DeveloperFirstName {
        if (developerFirstName == null || developerFirstName.isBlank()) {
            throw new IllegalArgumentException("Developer first name cannot be null");
        }
    }
}
