package com.userservice.user.domain.model.valueobjects.enterprise;

import jakarta.persistence.Embeddable;

@Embeddable
public record EnterpriseProfileImgUrl(String enterpriseProfileImgUrl) {
    public EnterpriseProfileImgUrl() { this(null); }

    public EnterpriseProfileImgUrl {
        if (enterpriseProfileImgUrl == null || enterpriseProfileImgUrl.isBlank()) {
            throw new IllegalArgumentException("Enterprise profile image url cannot be null");
        }
    }
}
