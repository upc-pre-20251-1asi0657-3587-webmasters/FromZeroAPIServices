package com.userservice.user.domain.model.valueobjects.enterprise;

import jakarta.persistence.Embeddable;

@Embeddable
public record EnterpriseProfileImgUrl(String enterpriseProfileImgUrl) {
    public EnterpriseProfileImgUrl() { this("https://cdn-icons-png.flaticon.com/512/3237/3237472.png"); }

    public EnterpriseProfileImgUrl {
        if (enterpriseProfileImgUrl == null || enterpriseProfileImgUrl.isBlank()) {
            throw new IllegalArgumentException("Enterprise profile image url cannot be null");
        }
    }
}
