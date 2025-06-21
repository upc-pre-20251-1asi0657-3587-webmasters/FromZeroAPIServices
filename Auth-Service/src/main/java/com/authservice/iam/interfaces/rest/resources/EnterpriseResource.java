package com.authservice.iam.interfaces.rest.resources;

import java.util.List;

public record EnterpriseResource(String id, String userEmail, List<String> roles) {
}
