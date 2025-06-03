package com.example.projectservice.deliverables.interfaces.rest.transform;


import com.example.projectservice.deliverables.domain.model.aggregates.Deliverable;
import com.example.projectservice.deliverables.interfaces.rest.resources.DeliverableResource;

public class DeliverableResourceFromEntityAssembler {
    public static DeliverableResource toResourceFromEntity(Deliverable deliverable) {
        return new DeliverableResource(
                deliverable.getId(),
                deliverable.getName(),
                deliverable.getDescription(),
                deliverable.getDeadline(),
                deliverable.getState(),
                deliverable.getDeveloperDescription(),
                deliverable.getProject().getId()
        );
    }
}