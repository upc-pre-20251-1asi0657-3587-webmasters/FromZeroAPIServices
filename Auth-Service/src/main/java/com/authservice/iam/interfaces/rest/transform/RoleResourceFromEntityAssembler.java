package com.authservice.iam.interfaces.rest.transform;

import com.authservice.iam.domain.model.entities.Role;
import com.authservice.iam.interfaces.rest.resources.RoleResource;

public class RoleResourceFromEntityAssembler {
    public static RoleResource toResourceFromEntity(Role entity) {
        return new RoleResource(entity.getRoleId(), entity.getStringName());
    }
}
