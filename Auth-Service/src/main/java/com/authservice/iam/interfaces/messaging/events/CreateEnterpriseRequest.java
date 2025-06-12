package com.authservice.iam.interfaces.messaging.events;

import java.util.UUID;

public record CreateEnterpriseRequest(UUID enterpriseId, String enterpriseEmail) {
}
