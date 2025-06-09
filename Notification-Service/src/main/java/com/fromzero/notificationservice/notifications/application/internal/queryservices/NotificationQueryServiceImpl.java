package com.fromzero.notificationservice.notifications.application.internal.queryservices;

import com.fromzero.notificationservice.notifications.domain.model.aggregates.Notification;
import com.fromzero.notificationservice.notifications.domain.model.queries.GetAllNotificationsQuery;
import com.fromzero.notificationservice.notifications.domain.model.queries.GetNotificationsByUserIdQuery;
import com.fromzero.notificationservice.notifications.domain.services.NotificationQueryService;
import com.fromzero.notificationservice.notifications.infrastructure.persistence.jpa.repositories.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationQueryServiceImpl implements NotificationQueryService {
    private final NotificationRepository notificationRepository;

    public NotificationQueryServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public List<Notification> handle(GetAllNotificationsQuery query) {
        var notifications = this.notificationRepository.findAll();
        return notifications;
    }

    @Override
    public List<Notification> handle(GetNotificationsByUserIdQuery query) {
        var notifications = this.notificationRepository.findAllByUserID(query.userId());
        return notifications;
    }

}
