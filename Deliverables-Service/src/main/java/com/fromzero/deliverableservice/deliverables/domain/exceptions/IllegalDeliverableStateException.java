package com.fromzero.deliverableservice.deliverables.domain.exceptions;

public class IllegalDeliverableStateException extends RuntimeException {
    public IllegalDeliverableStateException(String message) {
        super(message);
    }
}
