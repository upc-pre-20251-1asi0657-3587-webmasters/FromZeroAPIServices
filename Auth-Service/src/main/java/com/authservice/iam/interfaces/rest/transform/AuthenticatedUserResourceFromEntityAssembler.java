package com.authservice.iam.interfaces.rest.transform;

import com.authservice.iam.domain.model.aggregates.User;
import com.authservice.iam.interfaces.rest.resources.AuthenticatedUserResource;

public class AuthenticatedUserResourceFromEntityAssembler {
    public static AuthenticatedUserResource toResourceFromEntity(User entity, String token) {
        return new AuthenticatedUserResource(entity.getUserId().toString(), entity.getUserEmail(), entity.getUserRole(), token);
    }
}
