package com.authservice.iam.interfaces.rest.resources;

public record AuthenticatedUserResource(String userId, String userEmail, String userRole, String token) {
}
