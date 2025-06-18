package com.userservice.user.interfaces.rest.resources;

import java.util.UUID;

public record CreateEnterpriseResource(UUID enterpriseId, String enterpriseEmail, String enterpriseName) {
}
