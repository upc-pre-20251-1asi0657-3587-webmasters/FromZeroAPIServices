package com.example.projectservice.deliverables.domain.model.commands;

public record CreateDeliverableCommand(
        String name, String description, String date, Long projectId, int orderNumber) {
}
