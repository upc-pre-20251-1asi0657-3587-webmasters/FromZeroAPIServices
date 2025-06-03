package com.example.projectservice.deliverables.domain.model.commands;

public record UpdateDeliverableCommand(Long deliverableId, String name, String description, String date) {
}