package com.userservice.user.domain.model.valueobjects.developer;

import jakarta.persistence.Embeddable;

@Embeddable
public record DeveloperProfileImgUrl(String developerProfileImgUrl) {
    public DeveloperProfileImgUrl() { this(null); }

    public DeveloperProfileImgUrl {
        if (developerProfileImgUrl == null || developerProfileImgUrl.isBlank()) {
            throw new IllegalArgumentException("Developer profile img url cannot be null");
        }
    }
}
