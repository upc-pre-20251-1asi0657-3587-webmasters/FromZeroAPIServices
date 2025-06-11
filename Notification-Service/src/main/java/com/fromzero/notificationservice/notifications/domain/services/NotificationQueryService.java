package com.fromzero.notificationservice.notifications.domain.services;

import com.fromzero.notificationservice.notifications.domain.model.aggregates.Notification;
import com.fromzero.notificationservice.notifications.domain.model.queries.GetAllNotificationsQuery;
import com.fromzero.notificationservice.notifications.domain.model.queries.GetNotificationsByUserIdQuery;

import java.util.List;
import java.util.Optional;

public interface NotificationQueryService {
    List<Notification> handle(GetAllNotificationsQuery query);
    List<Notification> handle(GetNotificationsByUserIdQuery query);
}
