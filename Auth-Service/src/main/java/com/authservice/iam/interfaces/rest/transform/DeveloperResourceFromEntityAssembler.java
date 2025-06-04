package com.authservice.iam.interfaces.rest.transform;

import com.authservice.iam.domain.model.aggregates.User;
import com.authservice.iam.interfaces.rest.resources.DeveloperResource;

public class DeveloperResourceFromEntityAssembler {
    public static DeveloperResource toResourceFromEntity(User entity) {
        return new DeveloperResource(entity.getUserId().toString(), entity.getUserEmail(), entity.getUserRole());
    }
}
