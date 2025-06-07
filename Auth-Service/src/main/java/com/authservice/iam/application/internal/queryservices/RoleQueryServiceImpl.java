package com.authservice.iam.application.internal.queryservices;

import com.authservice.iam.domain.model.entities.Role;
import com.authservice.iam.domain.services.RoleQueryService;
import com.authservice.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleQueryServiceImpl implements RoleQueryService {
    private final RoleRepository roleRepository;

    public RoleQueryServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> handle() {
        return roleRepository.findAll();
    }
}
