package com.userservice.user.domain.model.valueobjects.enterprise;

import jakarta.persistence.Embeddable;

@Embeddable
public record EnterprisePhone(String enterprisePhone) {
    public EnterprisePhone() { this(null); }

    public EnterprisePhone {
        if (enterprisePhone == null || enterprisePhone.isBlank()) {
            throw new IllegalArgumentException("Enterprise phone cannot be null");
        }
    }
}
