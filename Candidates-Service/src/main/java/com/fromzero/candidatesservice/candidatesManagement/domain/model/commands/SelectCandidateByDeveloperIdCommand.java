package com.fromzero.candidatesservice.candidatesManagement.domain.model.commands;

import java.util.UUID;

public record SelectCandidateByDeveloperIdCommand(UUID developerId, Long projectId, String ownerId, String projectName, String ownerImgUrl) {

    public SelectCandidateByDeveloperIdCommand {
        if (developerId == null || projectId == null) {
            throw new IllegalArgumentException("Developer ID and Project ID cannot be null");
        }
    }
}
