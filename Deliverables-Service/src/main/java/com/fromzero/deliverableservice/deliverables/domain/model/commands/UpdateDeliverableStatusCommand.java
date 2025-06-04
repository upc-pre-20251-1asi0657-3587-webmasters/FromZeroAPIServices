package com.fromzero.deliverableservice.deliverables.domain.model.commands;

public record UpdateDeliverableStatusCommand(Long deliverableId, Boolean accepted) {
}
