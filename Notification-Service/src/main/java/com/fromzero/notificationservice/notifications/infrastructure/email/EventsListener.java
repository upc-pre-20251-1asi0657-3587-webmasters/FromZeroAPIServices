package com.fromzero.notificationservice.notifications.infrastructure.email;

import com.fromzero.notificationservice.notifications.domain.model.aggregates.Account;
import com.fromzero.notificationservice.notifications.domain.model.aggregates.Notification;
import com.fromzero.notificationservice.notifications.domain.model.commands.CreateNotificationCommand;
import com.fromzero.notificationservice.notifications.domain.model.events.DeveloperAppliedEvent;
import com.fromzero.notificationservice.notifications.domain.model.events.DeveloperSelectedEvent;
import com.fromzero.notificationservice.notifications.domain.model.events.UserCreatedEvent;
import com.fromzero.notificationservice.notifications.domain.model.events.DeliverableCreatedEvent;
import com.fromzero.notificationservice.notifications.infrastructure.email.clients.ProjectDto;
import com.fromzero.notificationservice.notifications.infrastructure.email.clients.ProjectServiceClient;
import com.fromzero.notificationservice.notifications.infrastructure.persistence.jpa.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;


@Component
public class EventsListener {

    private final EmailSender emailSender;
    private final NotificationRepository notificationRepository;
    private final AccountRepository accountRepository;

    @Autowired
    private AccountRepository internalUserEmailRepository;

    private ProjectServiceClient projectServiceClient;


    public EventsListener(EmailSender emailSender, NotificationRepository notificationRepository, AccountRepository accountRepository) {
        this.emailSender = emailSender;
        this.notificationRepository = notificationRepository;
        this.accountRepository = accountRepository;
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

        // Send a welcome email to the user
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
        }
    }

    @JmsListener(destination = "deliverable.created")
    public void onDeliverableCreatedEvent(DeliverableCreatedEvent event) {
        System.out.println("Mensaje recibido: " + event.getDeliverableName());
        //Get project info from Project microservice
        ProjectDto project = projectServiceClient.getProjectById(event.getProjectId());
        UUID developerUuid = UUID.fromString(project.getDeveloperId());

        // Find dev email from the account repository
        Account account = accountRepository.findByUserId(developerUuid);

        //Create a notification for the developer
        System.out.println("Nuevo Deliverable, mandando correo a: " + account.getEmail());
        CreateNotificationCommand command = new CreateNotificationCommand(
                "New candidate", // title
                developerUuid    // userId
        );
        Notification notification = new Notification(command);
        notificationRepository.save(notification);

        //Send an email to the developer
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
        }
    }

    @JmsListener(destination = "developer.applied", containerFactory = "topicListenerFactory")
    public void onDeveloperAppliedEvent(DeveloperAppliedEvent event) {
        //Get the enterprise UUID from the project service
        ProjectDto project = projectServiceClient.getProjectById(event.getProjectId());
        UUID enterpriseUuid = UUID.fromString(project.getOwnerId());

        //Find the enterprise email from the account repository
        Account account = accountRepository.findByUserId(enterpriseUuid);

        //Create a notification for the enterprise
        System.out.println("Nuevo Postulante, mandando correo a: " + account.getEmail());
        CreateNotificationCommand command = new CreateNotificationCommand(
                "New candidate", // title
                enterpriseUuid    // userId
        );
        Notification notification = new Notification(command);
        notificationRepository.save(notification);

        //Send an email to the enterprise
        try {
            emailSender.sendEmail(
                    account.getEmail(),
                    "You got a new candidate!",
                    "You have a new candidate for a project",
                    "The candidate " + event.getDeveloperId() + " has applied for your project: " + project.getName(),
                    "Please do not reply to this email. If you need assistance, contact us at support@fromzero.com"
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @JmsListener(destination = "developer.selected", containerFactory = "topicListenerFactory")
    public void onDeveloperSelectedEvent(DeveloperSelectedEvent event) {
        //Find the developers email from the account repository
        UUID developerUuid = UUID.fromString(event.getDeveloperId());
        Account account = accountRepository.findByUserId(developerUuid);
        ProjectDto project = projectServiceClient.getProjectById(event.getProjectId());

        //Create a notification for the developer
        System.out.println("Nuevo Postulante, mandando correo a: " + account.getEmail());
        CreateNotificationCommand command = new CreateNotificationCommand(
                "Selected project developer", // title
                developerUuid    // userId
        );
        Notification notification = new Notification(command);
        notificationRepository.save(notification);

        //Send an email to the developer
        try {
            emailSender.sendEmail(
                    account.getEmail(),
                    "You have been selected for a project!",
                    "Congratulations " + account.getName() + "!!!",
                    "You have been selected for the project: " + project.getName() + "contact the owner for more details.",
                    "Please do not reply to this email. If you need assistance, contact us at support@fromzero.com"
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}