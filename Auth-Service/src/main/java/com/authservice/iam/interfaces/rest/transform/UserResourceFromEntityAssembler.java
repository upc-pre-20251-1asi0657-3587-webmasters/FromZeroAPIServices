package com.authservice.iam.interfaces.rest.transform;

import com.authservice.iam.domain.model.aggregates.User;
import com.authservice.iam.interfaces.rest.resources.UserResource;

import java.util.Collections;

public class UserResourceFromEntityAssembler {
    public static UserResource toResourceFromEntity(User entity) {
        var roles = Collections.singletonList(entity.getUserRole());
        return new UserResource(entity.getUserId(), entity.getUserEmail(), roles);
    }
}
