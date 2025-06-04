package com.authservice.iam.interfaces.rest.resources;

public record SignUpDeveloperResource(String userEmail, String userPassword, String userRole) {
}
