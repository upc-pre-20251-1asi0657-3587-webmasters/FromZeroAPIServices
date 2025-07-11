package com.userservice.user.domain.model.valueobjects.enterprise;

import jakarta.persistence.Embeddable;

@Embeddable
public record EnterpriseEmail(String enterpriseEmail) {
    public EnterpriseEmail() { this("No email provided"); }

    public EnterpriseEmail {
        if (enterpriseEmail == null || enterpriseEmail.isBlank()) {
            throw new IllegalArgumentException("Enterprise Email cannot be empty");
        }
    }
}
