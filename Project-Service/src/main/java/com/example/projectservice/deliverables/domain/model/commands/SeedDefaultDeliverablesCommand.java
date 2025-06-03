package com.example.projectservice.deliverables.domain.model.commands;

import com.example.projectservice.projects.domain.valueobjects.ProjectTypeEnum;

public record SeedDefaultDeliverablesCommand(ProjectTypeEnum projectTypeEnum) {
}
