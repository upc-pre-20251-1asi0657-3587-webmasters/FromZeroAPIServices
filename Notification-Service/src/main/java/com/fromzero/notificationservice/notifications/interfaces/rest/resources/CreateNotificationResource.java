package com.fromzero.notificationservice.notifications.interfaces.rest.resources;

import java.util.UUID;

public record CreateNotificationResource(String Title, UUID UserId){
    public CreateNotificationResource {
        if (Title == null) {
            System.out.println("Title is null");
            throw new NullPointerException("Title is null");
        }
        if (UserId == null) {
            System.out.println("UserId is null");
            throw new NullPointerException("UserId is null");
        }
    }
}