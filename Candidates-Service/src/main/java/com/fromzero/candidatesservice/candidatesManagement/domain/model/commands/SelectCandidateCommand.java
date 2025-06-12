package com.fromzero.candidatesservice.candidatesManagement.domain.model.commands;

import java.util.UUID;

public record SelectCandidateCommand(UUID candidateId, UUID projectId) {

    public SelectCandidateCommand {
        if (candidateId == null || projectId == null) {
            throw new IllegalArgumentException("Candidate ID and Project ID must not be null");
        }
    }

}
