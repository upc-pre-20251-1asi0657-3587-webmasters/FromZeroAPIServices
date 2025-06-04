package com.fromzero.deliverableservice.deliverables.domain.exceptions;

public class DeliverableWithoutUploadException extends RuntimeException {
    public DeliverableWithoutUploadException(String message) {
        super(message);
    }
}
