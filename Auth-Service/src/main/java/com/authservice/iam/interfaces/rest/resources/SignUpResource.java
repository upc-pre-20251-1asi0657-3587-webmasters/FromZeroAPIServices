package com.authservice.iam.interfaces.rest.resources;

public record SignUpResource(String userEmail, String userPassword, String userRole) {
}
