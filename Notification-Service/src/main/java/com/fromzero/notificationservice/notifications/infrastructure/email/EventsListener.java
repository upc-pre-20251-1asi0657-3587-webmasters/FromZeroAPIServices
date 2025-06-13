package com.fromzero.notificationservice.notifications.infrastructure.email;

import com.fromzero.notificationservice.notifications.domain.model.aggregates.Account;
import com.fromzero.notificationservice.notifications.domain.model.events.UserCreatedEvent;
import com.fromzero.notificationservice.notifications.domain.model.events.DeliverableCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class EventsListener {

    private final EmailSender emailSender;

    @Autowired
    private AccountRepository internalUserEmailRepository;


    public EventsListener(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    @JmsListener(destination = "deliverable.created")
    public void onDeliverableCreatedEvent(DeliverableCreatedEvent event) {
        System.out.println("Mensaje recibido: " + event.getDeliverableName());
    }

    @JmsListener(destination = "account.created")
    public void onAccountCreatedEvent(UserCreatedEvent event) {
        // To save:
        internalUserEmailRepository.save(new Account(event.getUserId(), event.getEmail(), event.getName(), event.getRole()));

        System.out.println("Cuenta creada, mandando correo a: " + event.getEmail());
//        System.out.println("Account created for user" + event.getName()
//                + "email: " + event.getEmail()
//                + " with role: " + event.getRole()
//                + " and userId: " + event.getUserId());
        try {
            emailSender.sendEmail(
                    event.getEmail(),
                    "Welcome to FromZero",
                    "Your account has been created successfully.",
                    "Thank you " + event.getName() + " for joining us as a " + event.getRole(),
                    "Please do not reply to this email. If you need assistance, contact us at support@fromzero.com"
            );
        } catch (IOException e) {
            e.printStackTrace();
            // Optionally, log or handle the error appropriately
        }
    }
}