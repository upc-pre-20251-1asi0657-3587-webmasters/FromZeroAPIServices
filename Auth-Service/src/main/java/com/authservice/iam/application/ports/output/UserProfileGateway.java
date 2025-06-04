package com.authservice.iam.application.ports.output;

import java.util.UUID;

public interface UserProfileGateway {
    void createDeveloperProfile(UUID uuid, String email);
    void createEnterpriseProfile(UUID uuid, String email);
}
