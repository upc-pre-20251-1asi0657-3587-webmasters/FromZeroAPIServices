package com.userservice.user.interfaces.rest.resources;

import java.util.UUID;

public record CreateDeveloperResource(UUID developerId, String developerEmail, String developerFirstName, String developerLastName) {
}
