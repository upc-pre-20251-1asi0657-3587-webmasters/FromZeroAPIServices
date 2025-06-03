package com.authservice.iam.domain.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String user) {
        super("User " + user + " not found");
    }
}
