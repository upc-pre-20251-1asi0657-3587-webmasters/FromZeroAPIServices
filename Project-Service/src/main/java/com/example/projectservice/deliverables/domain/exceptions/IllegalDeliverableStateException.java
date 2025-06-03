package com.example.projectservice.deliverables.domain.exceptions;

public class IllegalDeliverableStateException extends RuntimeException {
    public IllegalDeliverableStateException(String message) {
        super(message);
    }
}
