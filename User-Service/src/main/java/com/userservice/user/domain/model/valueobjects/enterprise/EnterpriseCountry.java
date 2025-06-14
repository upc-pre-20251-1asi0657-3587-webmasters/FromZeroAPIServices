package com.userservice.user.domain.model.valueobjects.enterprise;

import jakarta.persistence.Embeddable;

@Embeddable
public record EnterpriseCountry(String enterpriseCountry) {
    public EnterpriseCountry() { this("No country provided"); }

    public EnterpriseCountry {
        if (enterpriseCountry == null || enterpriseCountry.isBlank()) {
            throw new IllegalArgumentException("Enterprise country cannot be empty");
        }
    }
}
