package com.authservice.iam.domain.model.commands;

public record SignUpEnterpriseCommand(String userEmail, String userPassword, String userRole, String enterpriseName) {
}
