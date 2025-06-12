package com.fromzero.candidatesservice.candidatesManagement.interfaces.rest.resources;

import java.util.UUID;

public record ApplyToProjectResource(UUID developerId, String firstName, String lastName, String description)  {
    public ApplyToProjectResource {
        if (developerId == null) {
            throw new NullPointerException("developerId is null");
        }
        if (firstName == null || firstName.isBlank()) {
            throw new IllegalArgumentException("firstName must not be null or blank");
        }
        if (lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("lastName must not be null or blank");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("description must not be null or blank");
        }
    }
}
