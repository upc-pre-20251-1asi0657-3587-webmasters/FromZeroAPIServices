package com.fromzero.notificationservice.notifications.domain.model.events;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreatedEvent {
    //private String Name;
    private String Email;
    private String Role;
}
