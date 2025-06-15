package com.fromzero.notificationservice.notifications.infrastructure.email;

import com.fromzero.notificationservice.notifications.domain.model.aggregates.Account;
import com.fromzero.notificationservice.notifications.domain.model.aggregates.Notification;
import com.fromzero.notificationservice.notifications.domain.model.commands.CreateNotificationCommand;
import com.fromzero.notificationservice.notifications.domain.model.events.UserCreatedEvent;
import com.fromzero.notificationservice.notifications.domain.model.events.DeliverableCreatedEvent;
import com.fromzero.notificationservice.notifications.infrastructure.email.clients.ProjectDto;
import com.fromzero.notificationservice.notifications.infrastructure.email.clients.ProjectServiceClient;
import com.fromzero.notificationservice.notifications.infrastructure.persistence.jpa.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;


@Component
public class EventsListener {

    private final EmailSender emailSender;
    private final NotificationRepository notificationRepository;
    private final AccountRepository accountRepository;

    @Autowired
    private AccountRepository internalUserEmailRepository;

    @Autowired
    private ProjectServiceClient projectServiceClient;


    public EventsListener(EmailSender emailSender, NotificationRepository notificationRepository, AccountRepository accountRepository) {
        this.emailSender = emailSender;
        this.notificationRepository = notificationRepository;
        this.accountRepository = accountRepository;
    }

    @JmsListener(destination = "deliverable.created")
    public void onDeliverableCreatedEvent(DeliverableCreatedEvent event) {
        System.out.println("Mensaje recibido: " + event.getDeliverableName());
        // 1. Get project info from Project microservice
        ProjectDto project = projectServiceClient.getProjectById(event.getProjectId());
        String developerUuid = project.getDeveloperId();

        // 2. Create a notification for the developer
        Account account = accountRepository.findByUserId(UUID.fromString(developerUuid));

        // 3. Create the notification
        System.out.println("Nuevo Deliverable, mandando correo a: " + account.getEmail());
        try {
            emailSender.sendEmail(
                    account.getEmail(),
                    "New Deliverable Created!!!",
                    "The new deliverable is due: " + event.getDeadline(),
                    "The new Deliverable: " + event.getDeliverableName() + " has been created for the project: " + project.getName(),
                    "Please do not reply to this email. If you need assistance, contact us at support@fromzero.com"
            );
        } catch (IOException e) {
            e.printStackTrace();
            // Optionally, log or handle the error appropriately
        }
    }

    @JmsListener(destination = "account.created")
    public void onAccountCreatedEvent(UserCreatedEvent event) {
        // Save the account information to the internal user email repository and create a notification
        internalUserEmailRepository.save(new Account(event.getUserId(), event.getEmail(), event.getName(), event.getRole()));
        CreateNotificationCommand command = new CreateNotificationCommand(
                "Welcome to FromZero", // title
                event.getUserId()      // userId
        );
        Notification notification = new Notification(command);
        notificationRepository.save(notification);

        System.out.println("Cuenta creada, mandando correo a: " + event.getEmail());

//        try {
//            emailSender.sendEmail(
//                    event.getEmail(),
//                    "Welcome to FromZero",
//                    "Your account has been created successfully.",
//                    "Thank you " + event.getName() + " for joining us as a " + event.getRole(),
//                    "Please do not reply to this email. If you need assistance, contact us at support@fromzero.com"
//            );
//        } catch (IOException e) {
//            e.printStackTrace();
//            // Optionally, log or handle the error appropriately
//        }
    }
}