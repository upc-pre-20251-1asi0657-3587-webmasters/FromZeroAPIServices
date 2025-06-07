package com.authservice.iam.domain.services;

import com.authservice.iam.domain.model.entities.Role;

import java.util.List;

public interface RoleQueryService {
    List<Role> handle();
}
