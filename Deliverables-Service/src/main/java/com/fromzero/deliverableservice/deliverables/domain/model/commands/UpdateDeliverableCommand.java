package com.fromzero.deliverableservice.deliverables.domain.model.commands;

public record UpdateDeliverableCommand(Long deliverableId, String name, String description, String date) {
}