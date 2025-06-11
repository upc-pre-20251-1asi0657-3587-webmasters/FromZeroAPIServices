package com.fromzero.notificationservice.notifications.application.internal.commandservices;

import com.fromzero.notificationservice.notifications.domain.model.aggregates.Notification;
import com.fromzero.notificationservice.notifications.domain.model.commands.CreateNotificationCommand;
import com.fromzero.notificationservice.notifications.domain.services.NotificationCommandService;
import com.fromzero.notificationservice.notifications.infrastructure.persistence.jpa.repositories.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NotificationCommandServiceImpl implements NotificationCommandService {
    private final NotificationRepository notificationRepository;

    public NotificationCommandServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public Optional<Notification> handle(CreateNotificationCommand command) {
        var notification = new Notification(command);
        this.notificationRepository.save(notification);
        return Optional.of(notification);
    };
}
