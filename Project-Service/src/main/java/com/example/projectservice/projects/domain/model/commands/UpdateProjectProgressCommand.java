package com.example.projectservice.projects.domain.model.commands;


public record UpdateProjectProgressCommand(Long projectId, double progress) {
}
