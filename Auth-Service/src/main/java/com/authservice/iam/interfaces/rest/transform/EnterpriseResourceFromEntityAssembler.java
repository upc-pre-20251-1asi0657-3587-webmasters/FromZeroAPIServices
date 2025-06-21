package com.authservice.iam.interfaces.rest.transform;

import com.authservice.iam.domain.model.aggregates.User;
import com.authservice.iam.interfaces.rest.resources.EnterpriseResource;

import java.util.Collections;

public class EnterpriseResourceFromEntityAssembler {
    public static EnterpriseResource toResourceFromEntity(User entity) {
        var roles = Collections.singletonList(entity.getUserRole());
        return new EnterpriseResource(entity.getUserId().toString(), entity.getUserEmail(), roles);
    }
}
