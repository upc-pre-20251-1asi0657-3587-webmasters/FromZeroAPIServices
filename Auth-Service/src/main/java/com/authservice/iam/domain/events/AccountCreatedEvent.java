package com.authservice.iam.domain.events;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// DeliverableCreatedEvent.java
public class AccountCreatedEvent {
    private String userEmail;
    private String Role;
}