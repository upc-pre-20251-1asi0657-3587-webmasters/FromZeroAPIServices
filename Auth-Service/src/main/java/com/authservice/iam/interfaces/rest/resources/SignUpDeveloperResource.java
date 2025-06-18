package com.authservice.iam.interfaces.rest.resources;

public record SignUpDeveloperResource(String userEmail, String password, String firstName, String lastName) {
}
