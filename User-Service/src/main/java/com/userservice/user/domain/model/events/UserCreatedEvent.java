package com.userservice.user.domain.model.events;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserCreatedEvent {
    private UUID userId;
    private String Name;
    private String Email;
    private String Role;
}
