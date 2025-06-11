package com.fromzero.notificationservice.notifications.interfaces.rest.resources;

import java.util.UUID;

public record NotificationResource(UUID Id, String Title, UUID UserId) {
}
