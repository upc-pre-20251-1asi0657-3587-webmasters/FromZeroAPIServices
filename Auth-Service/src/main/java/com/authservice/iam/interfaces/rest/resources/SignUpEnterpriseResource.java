package com.authservice.iam.interfaces.rest.resources;

public record SignUpEnterpriseResource(String userEmail, String userPassword, String userRole) {
}
