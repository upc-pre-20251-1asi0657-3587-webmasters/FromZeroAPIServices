package com.fromzero.candidatesservice.candidatesManagement.interfaces.rest.transform;


import com.fromzero.candidatesservice.candidatesManagement.domain.model.aggregates.Candidate;
import com.fromzero.candidatesservice.candidatesManagement.interfaces.rest.resources.CandidateResource;

public class CandidateResourceFromEntityAssembler {

    public static CandidateResource toResourceFromEntity(Candidate entity) {
        return new CandidateResource(
                entity.getFirstName(),
                entity.getLastName(),
                entity.getDescription(),
                entity.getDeveloperId(),
                entity.getProjectId()

        );
    }
}
