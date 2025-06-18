package com.userservice.user.domain.model.valueobjects.enterprise;

import jakarta.persistence.Embeddable;

@Embeddable
public record EnterpriseRuc(String enterpriseRuc) {
    public EnterpriseRuc() { this("No ruc provided"); }

    public EnterpriseRuc {
        if (enterpriseRuc == null ||  enterpriseRuc.isBlank()) {
            throw new IllegalStateException("Enterprise ruc is null");
        }
    }
}
