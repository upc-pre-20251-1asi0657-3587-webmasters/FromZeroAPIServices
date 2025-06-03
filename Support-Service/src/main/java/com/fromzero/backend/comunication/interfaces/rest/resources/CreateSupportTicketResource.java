package com.fromzero.backend.comunication.interfaces.rest.resources;

public record CreateSupportTicketResource(String title, String type, String description, Long senderId) {
    public CreateSupportTicketResource {
        if (title == null) {
            System.out.println("title is null");
            throw new NullPointerException("title is null");
        }
        if (type == null) {
            System.out.println("type is null");
            throw new NullPointerException("type is null");
        }
        if (description == null) {
            System.out.println("description is null");
            throw new NullPointerException("description is null");
        }
        if (senderId == null) {
            System.out.println("senderId is null");
            throw new NullPointerException("senderId is null");
        }
    }
}
