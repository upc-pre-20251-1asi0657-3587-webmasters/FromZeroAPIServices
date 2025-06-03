package com.userservice.user.domain.exceptions;

public class CreateDeveloperFailedException extends RuntimeException {
    public CreateDeveloperFailedException() {
        super("Failed to create developer");
    }
}
