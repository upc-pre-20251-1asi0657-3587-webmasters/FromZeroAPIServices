package com.authservice.iam.domain.exceptions;

public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException() {
        super("User or password is invalid");
    }
}
