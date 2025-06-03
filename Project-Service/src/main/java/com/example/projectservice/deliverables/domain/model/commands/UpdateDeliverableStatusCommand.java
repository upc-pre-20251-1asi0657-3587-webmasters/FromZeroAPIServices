package com.example.projectservice.deliverables.domain.model.commands;

public record UpdateDeliverableStatusCommand(Long deliverableId, Boolean accepted) {
}
