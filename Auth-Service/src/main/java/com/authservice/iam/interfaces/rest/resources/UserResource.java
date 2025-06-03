package com.authservice.iam.interfaces.rest.resources;

import java.util.UUID;

public record UserResource(String userId, String userEmail, String userRole) {
}
