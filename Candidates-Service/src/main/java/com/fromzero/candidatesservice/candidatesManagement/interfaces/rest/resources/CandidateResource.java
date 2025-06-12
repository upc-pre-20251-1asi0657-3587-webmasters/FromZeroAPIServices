package com.fromzero.candidatesservice.candidatesManagement.interfaces.rest.resources;

import java.util.UUID;

public record CandidateResource(
        String firstName, String lastName, String description, UUID developerId, UUID projectId
) {
    public CandidateResource {
        if (firstName == null) {
            throw new NullPointerException("Firstname is null");
        }
        if (lastName == null) {
            throw new NullPointerException("lastName is null");
        }
        if (description == null) {
            throw new NullPointerException("description is null");
        }

        if (developerId == null) {
            throw new NullPointerException("developerId is null");
        }
        if(projectId == null) {
            throw new NullPointerException("projectId is null");
        }
    }
}
