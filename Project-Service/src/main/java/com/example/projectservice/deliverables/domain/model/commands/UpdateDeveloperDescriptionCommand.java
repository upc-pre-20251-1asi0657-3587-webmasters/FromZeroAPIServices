package com.example.projectservice.deliverables.domain.model.commands;

public record UpdateDeveloperDescriptionCommand(Long deliverableId, String message, Long project) {
}
