package com.fromzero.deliverableservice.deliverables.interfaces.rest.resources;


import com.fromzero.deliverableservice.deliverables.domain.valueobjects.DeliverableStatus;

public record DeliverableResource(
        Long id, String name, String description, java.time.LocalDateTime date, DeliverableStatus state, String developerMessage, String projectId) {
}
