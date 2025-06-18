package com.authservice.iam.interfaces.rest.resources;

public record SignUpEnterpriseResource(String userEmail, String password, String enterpriseName) {
}
