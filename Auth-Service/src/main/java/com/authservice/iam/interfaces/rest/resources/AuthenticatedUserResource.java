package com.authservice.iam.interfaces.rest.resources;

public record AuthenticatedUserResource(String id, String userEmail, String roles, String token) {
}
