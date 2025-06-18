package com.fromzero.deliverableservice.deliverables.domain.model.commands;

public record UpdateProjectProgressCommand(Long projectId, double progress) {
}
