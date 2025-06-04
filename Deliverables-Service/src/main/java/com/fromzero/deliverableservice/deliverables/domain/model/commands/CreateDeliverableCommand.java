package com.fromzero.deliverableservice.deliverables.domain.model.commands;

public record CreateDeliverableCommand(
        String name, String description, String date, String projectId, int orderNumber) {
}
