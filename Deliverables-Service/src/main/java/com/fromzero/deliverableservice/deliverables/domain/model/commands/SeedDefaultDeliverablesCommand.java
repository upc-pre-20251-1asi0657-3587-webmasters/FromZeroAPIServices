package com.fromzero.deliverableservice.deliverables.domain.model.commands;

import com.fromzero.deliverableservice.deliverables.domain.valueobjects.ProjectTypeEnum;

public record SeedDefaultDeliverablesCommand(ProjectTypeEnum projectTypeEnum) {
}
