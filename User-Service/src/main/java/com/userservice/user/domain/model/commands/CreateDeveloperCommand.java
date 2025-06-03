package com.userservice.user.domain.model.commands;

import java.util.UUID;

public record CreateDeveloperCommand(UUID developerId, String developerEmail) {
}
