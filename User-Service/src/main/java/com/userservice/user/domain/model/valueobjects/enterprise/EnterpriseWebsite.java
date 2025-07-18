package com.userservice.user.domain.model.valueobjects.enterprise;

import jakarta.persistence.Embeddable;

@Embeddable
public record EnterpriseWebsite(String enterpriseWebsite) {
    public EnterpriseWebsite() { this("No website provided"); }

    public EnterpriseWebsite {
        if (enterpriseWebsite == null ||  enterpriseWebsite.isBlank()) {
            throw new IllegalArgumentException("Enterprise website cannot be null");
        }
    }
}
