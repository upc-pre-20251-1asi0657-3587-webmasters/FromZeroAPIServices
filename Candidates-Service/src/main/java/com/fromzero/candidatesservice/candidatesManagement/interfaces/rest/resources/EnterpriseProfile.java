package com.fromzero.candidatesservice.candidatesManagement.interfaces.rest.resources;

import java.util.UUID;

public record EnterpriseProfile(
        UUID id, String enterpriseName, String profileImgUrl) {
}
