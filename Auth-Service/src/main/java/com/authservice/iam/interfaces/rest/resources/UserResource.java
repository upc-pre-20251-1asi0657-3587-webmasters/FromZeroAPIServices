package com.authservice.iam.interfaces.rest.resources;

import java.util.List;
import java.util.UUID;

public record UserResource(UUID id, String userEmail, List<String> roles) {
}
