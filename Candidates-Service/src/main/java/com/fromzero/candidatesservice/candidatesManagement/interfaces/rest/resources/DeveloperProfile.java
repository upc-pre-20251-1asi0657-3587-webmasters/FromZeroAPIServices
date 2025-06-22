package com.fromzero.candidatesservice.candidatesManagement.interfaces.rest.resources;

import java.util.UUID;

public record DeveloperProfile(
        UUID id,
        String firstName,
        String lastName,
        String description
) {}