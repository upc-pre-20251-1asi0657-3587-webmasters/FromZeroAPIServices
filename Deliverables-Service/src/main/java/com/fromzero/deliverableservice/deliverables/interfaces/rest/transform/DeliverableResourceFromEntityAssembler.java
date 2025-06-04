package com.fromzero.deliverableservice.deliverables.interfaces.rest.transform;


import com.fromzero.deliverableservice.deliverables.domain.model.aggregates.Deliverable;
import com.fromzero.deliverableservice.deliverables.interfaces.rest.resources.DeliverableResource;

public class DeliverableResourceFromEntityAssembler {
    public static DeliverableResource toResourceFromEntity(Deliverable deliverable) {
        return new DeliverableResource(
                deliverable.getId(),
                deliverable.getName(),
                deliverable.getDescription(),
                deliverable.getDeadline(),
                deliverable.getState(),
                deliverable.getDeveloperDescription(),
                deliverable.getProjectId()
        );
    }
}