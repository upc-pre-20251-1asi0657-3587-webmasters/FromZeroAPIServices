package com.fromzero.notificationservice.notifications.domain.model.commands;

import java.util.UUID;

public record CreateNotificationCommand (String Title, UUID UserId){};
