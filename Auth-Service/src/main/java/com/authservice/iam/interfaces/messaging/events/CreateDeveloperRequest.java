package com.authservice.iam.interfaces.messaging.events;

import java.util.UUID;

public record CreateDeveloperRequest(UUID developerId, String developerEmail, String developerFirstName, String developerLastName) {
}
