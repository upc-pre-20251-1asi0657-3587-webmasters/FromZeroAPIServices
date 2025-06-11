package com.fromzero.notificationservice.notifications.interfaces.rest.transform;

import com.fromzero.notificationservice.notifications.domain.model.commands.CreateNotificationCommand;
import com.fromzero.notificationservice.notifications.interfaces.rest.resources.CreateNotificationResource;

public class CreateNotificationCommandFromResourceAssembler {
    public static CreateNotificationCommand toCommandFromResource(CreateNotificationResource resource) {
        return new CreateNotificationCommand(resource.Title(), resource.UserId());
    }
}
