package com.authservice.iam.domain.model.commands;

public record SignUpDeveloperCommand(String userEmail, String userPassword, String userRole) {
}
