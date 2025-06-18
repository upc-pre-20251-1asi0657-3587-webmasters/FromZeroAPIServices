package com.userservice.user.domain.model.valueobjects.enterprise;

import jakarta.persistence.Embeddable;

@Embeddable
public record EnterpriseDescription(String enterpriseDescription) {
    public EnterpriseDescription() { this("No description provided"); }

    public EnterpriseDescription {
        if (enterpriseDescription == null ||  enterpriseDescription.isBlank()) {
            throw new IllegalArgumentException("Enterprise description cannot be null");
        }
    }
}
