package com.fromzero.notificationservice.notifications.infrastructure.email;

import com.fromzero.notificationservice.notifications.domain.model.events.DeliverableCreatedEvent;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class DeliverableEventListener {

    @JmsListener(destination = "payment.queue")
    public void onPaymentEvent(DeliverableCreatedEvent event) {
        System.out.println("Mensaje recibido: " + event);
    }
}