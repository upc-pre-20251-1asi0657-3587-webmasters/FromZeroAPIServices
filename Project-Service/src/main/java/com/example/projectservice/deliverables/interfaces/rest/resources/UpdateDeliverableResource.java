package com.example.projectservice.deliverables.interfaces.rest.resources;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public record UpdateDeliverableResource(String name, String description, String date) {
    public UpdateDeliverableResource {
        if (name == null) {
            System.out.println("name is null");
            throw new NullPointerException("name is null");
        }
        if (description == null) {
            System.out.println("description is null");
            throw new NullPointerException("description is null");
        }
        if (date == null) {
            System.out.println("date is null");
            throw new NullPointerException("date is null");
        }
        try {
            LocalDateTime parsedDate = LocalDateTime.parse(date);
            if (parsedDate.isBefore(LocalDateTime.now())) {
                System.out.println("date is in the past");
                throw new IllegalArgumentException("date cannot be in the past");
            }
        } catch (DateTimeParseException e) {
            System.out.println("invalid date format");
            throw new IllegalArgumentException("invalid date format");
        }
    }
}