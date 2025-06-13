package com.authservice.iam.application.ports.output;

import java.util.UUID;

public interface UserProfileGateway {
    void createDeveloperProfile(UUID uuid, String email, String firstName, String lastName);
    void createEnterpriseProfile(UUID uuid, String email);
}
