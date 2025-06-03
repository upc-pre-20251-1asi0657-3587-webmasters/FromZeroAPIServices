package com.userservice.user.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public record DeveloperId(UUID developerId) {
    public DeveloperId() { this(null); };

    public DeveloperId {
        if (developerId == null) {
            throw new IllegalArgumentException("Developer id cannot be null");
        }
    }
}
