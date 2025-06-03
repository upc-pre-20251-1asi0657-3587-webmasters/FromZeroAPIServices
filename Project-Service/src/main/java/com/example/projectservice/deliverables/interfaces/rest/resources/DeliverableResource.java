package com.example.projectservice.deliverables.interfaces.rest.resources;


import com.example.projectservice.deliverables.domain.valueobjects.DeliverableStatus;

public record DeliverableResource(
        Long id, String name, String description, java.time.LocalDateTime date, DeliverableStatus state, String developerMessage, Long projectId) {
}
