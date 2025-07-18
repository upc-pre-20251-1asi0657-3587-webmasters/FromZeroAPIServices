package com.fromzero.notificationservice.notifications.infrastructure.email.clients;

import lombok.Getter;
import lombok.Setter;

// DTO for project response
@Getter
@Setter
public class ProjectDto {
    private String developerId; // UUID as String
    private String name;
    private String ownerId; // UUID as String
    // other fields as needed
}
