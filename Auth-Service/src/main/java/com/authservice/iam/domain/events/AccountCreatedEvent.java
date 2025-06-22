package com.authservice.iam.domain.events;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
// DeliverableCreatedEvent.java
public class AccountCreatedEvent {
    private String userEmail;
    private String Name;
    private UUID userId;
    private String Role;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }
}