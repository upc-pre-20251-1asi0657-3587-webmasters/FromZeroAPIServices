package com.authservice.iam.domain.model.commands;

public record SignUpCommand(String userEmail, String userPassword, String userRole) {
}
