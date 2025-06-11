package com.fromzero.notificationservice.notifications.infrastructure.email;

import com.fromzero.notificationservice.notifications.domain.model.events.UserCreatedEvent;
import com.fromzero.notificationservice.notifications.domain.model.events.DeliverableCreatedEvent;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class EventsListener {

    @JmsListener(destination = "deliverable.created")
    public void onDeliverableCreatedEvent(DeliverableCreatedEvent event) {
        System.out.println("Mensaje recibido: " + event.getDeliverableName());
    }

    @JmsListener(destination = "account.created")
    public void onAccountCreatedEvent(UserCreatedEvent event) {
        System.out.println("Account created for: " + event.getName() + "with email: " + event.getEmail() + " with role: " + event.getRole());

    }
}