package com.authservice.iam.domain.model.commands;

public record SignInCommand(String userEmail, String userPassword) {
}
