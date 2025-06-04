package com.fromzero.deliverableservice.deliverables.domain.model.commands;

public record UpdateDeveloperDescriptionCommand(Long deliverableId, String message, Long project) {
}
