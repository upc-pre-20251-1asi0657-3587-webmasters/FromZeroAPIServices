package com.authservice.iam.interfaces.rest.resources;

import java.util.List;

public record DeveloperResource(String id, String userEmail, List<String> roles) {
}
