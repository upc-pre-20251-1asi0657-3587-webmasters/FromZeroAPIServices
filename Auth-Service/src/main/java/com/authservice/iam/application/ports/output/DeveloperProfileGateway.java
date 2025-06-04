package com.authservice.iam.application.ports.output;

import java.util.UUID;

public interface DeveloperProfileGateway {
    void createDeveloperProfile(UUID uuid, String email);
}
