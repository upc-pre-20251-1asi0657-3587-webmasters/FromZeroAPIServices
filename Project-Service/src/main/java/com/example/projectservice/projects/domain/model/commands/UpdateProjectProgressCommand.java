package com.example.projectservice.projects.domain.model.commands;


import com.example.projectservice.projects.domain.model.aggregates.Project;

public record UpdateProjectProgressCommand(Project project, double progress) {
}
