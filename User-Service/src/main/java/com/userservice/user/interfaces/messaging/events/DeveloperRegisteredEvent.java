package com.userservice.user.interfaces.messaging.events;

import java.util.UUID;

public record DeveloperRegisteredEvent(UUID uuid, String email) {
}
