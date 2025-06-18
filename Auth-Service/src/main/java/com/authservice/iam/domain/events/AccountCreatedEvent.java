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
}