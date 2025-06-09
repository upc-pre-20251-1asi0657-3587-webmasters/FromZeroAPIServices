package com.fromzero.notificationservice.notifications.interfaces.rest.transform;

import com.fromzero.notificationservice.notifications.domain.model.aggregates.Notification;
import com.fromzero.notificationservice.notifications.interfaces.rest.resources.NotificationResource;

public class NotificationResourceFromEntityAssembler {
    public static NotificationResource toResourceFromEntity(Notification entity) {
        return new NotificationResource(
                entity.getId(),
                entity.getTitle(),
                entity.getUserID()
        );
    }
}
