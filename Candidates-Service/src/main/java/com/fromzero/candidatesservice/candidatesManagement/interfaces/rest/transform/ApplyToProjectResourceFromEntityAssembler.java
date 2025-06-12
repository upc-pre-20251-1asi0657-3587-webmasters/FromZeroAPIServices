package com.fromzero.candidatesservice.candidatesManagement.interfaces.rest.transform;


import com.fromzero.candidatesservice.candidatesManagement.domain.model.aggregates.Candidate;
import com.fromzero.candidatesservice.candidatesManagement.interfaces.rest.resources.ApplyToProjectResource;

public class ApplyToProjectResourceFromEntityAssembler {

    public static ApplyToProjectResource toResourceFromEntity (Candidate entity) {
        return new ApplyToProjectResource(entity.getDeveloperId(), entity.getFirstName(), entity.getLastName(), entity.getDescription());
    }
}
