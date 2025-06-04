package com.authservice.iam.interfaces.rest.transform;

import com.authservice.iam.domain.model.aggregates.User;
import com.authservice.iam.interfaces.rest.resources.EnterpriseResource;

public class EnterpriseResourceFromEntityAssembler {
    public static EnterpriseResource toResourceFromEntity(User entity) {
        return new EnterpriseResource(entity.getUserId().toString(), entity.getUserEmail(), entity.getUserRole());
    }
}
