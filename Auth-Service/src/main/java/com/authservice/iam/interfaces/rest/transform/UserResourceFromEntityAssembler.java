package com.authservice.iam.interfaces.rest.transform;

import com.authservice.iam.domain.model.aggregates.User;
import com.authservice.iam.interfaces.rest.resources.UserResource;

public class UserResourceFromEntityAssembler {
    public static UserResource toResourceFromEntity(User entity) {
        return new UserResource(entity.getUserId().toString(), entity.getUserEmail(), entity.getUserRole());
    }
}
