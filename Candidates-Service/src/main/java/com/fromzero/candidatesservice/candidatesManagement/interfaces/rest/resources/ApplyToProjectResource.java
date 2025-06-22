package com.fromzero.candidatesservice.candidatesManagement.interfaces.rest.resources;

import java.util.UUID;

public record ApplyToProjectResource(UUID developerId)  {
    public ApplyToProjectResource {
        if (developerId == null) {
            throw new NullPointerException("developerId is null");
        }
    }
}
