package com.userservice.user.domain.model.valueobjects.enterprise;

import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public record EnterpriseId(UUID enterpriseId) {
    public EnterpriseId() { this(null); }

    public EnterpriseId {
        if (enterpriseId == null) {
            throw new IllegalArgumentException("Enterprise id cannot be null");
        }
    }
}
