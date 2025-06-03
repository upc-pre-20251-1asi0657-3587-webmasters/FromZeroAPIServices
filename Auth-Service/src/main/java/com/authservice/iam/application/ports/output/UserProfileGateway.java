package com.authservice.iam.application.ports.output;

import java.util.UUID;

public interface UserProfileGateway {
    void createUserProfile(UUID uuid, String email);
}
