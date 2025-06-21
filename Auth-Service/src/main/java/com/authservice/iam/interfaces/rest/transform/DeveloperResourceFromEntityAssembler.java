package com.authservice.iam.interfaces.rest.transform;

import com.authservice.iam.domain.model.aggregates.User;
import com.authservice.iam.interfaces.rest.resources.DeveloperResource;

import java.util.Collections;

public class DeveloperResourceFromEntityAssembler {
    public static DeveloperResource toResourceFromEntity(User entity) {
        var roles = Collections.singletonList(entity.getUserRole());
        return new DeveloperResource(entity.getUserId().toString(), entity.getUserEmail(), roles);
    }
}
