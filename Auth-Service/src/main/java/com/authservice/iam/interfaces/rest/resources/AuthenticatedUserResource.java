package com.authservice.iam.interfaces.rest.resources;

import java.util.List;

public record AuthenticatedUserResource(String id, String userEmail, List<String> roles, String token) {
}
