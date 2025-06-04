package com.userservice.user.domain.model.commands;

import java.util.UUID;

public record CreateEnterpriseCommand(UUID enterpriseId, String enterpriseEmail) {
}
