package com.authservice.iam.interfaces.rest.transform;

import com.authservice.iam.domain.model.aggregates.User;
import com.authservice.iam.interfaces.rest.resources.AuthenticatedUserResource;

import java.util.Collections;

public class AuthenticatedUserResourceFromEntityAssembler {
    public static AuthenticatedUserResource toResourceFromEntity(User entity, String token) {
        var roles = Collections.singletonList(entity.getUserRole());
        return new AuthenticatedUserResource(entity.getUserId().toString(), entity.getUserEmail(), roles, token);
    }
}
