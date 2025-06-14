package com.userservice.user.domain.model.valueobjects.developer;

public record DeveloperDescription(String developerDescription) {
    public DeveloperDescription() { this(null); }

    public DeveloperDescription {
        if (developerDescription == null || developerDescription.isBlank()) {
            throw new IllegalArgumentException("Developer description cannot be null");
        }
    }
}
