package com.userservice.user.domain.model.valueobjects;

public record DeveloperPhone(String developerPhone) {
    public DeveloperPhone() { this(null); }

    public DeveloperPhone {
        if (developerPhone == null || developerPhone.isBlank()) {
            throw new IllegalArgumentException("Developer phone cannot be null");
        }
    }
}
