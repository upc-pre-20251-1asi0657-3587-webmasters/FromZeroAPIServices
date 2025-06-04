package com.authservice.iam.infrastructure.init;

import com.authservice.iam.domain.model.entities.Role;
import com.authservice.iam.domain.model.valueobjects.Roles;
import com.authservice.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineRoleRunner implements CommandLineRunner {
    private final RoleRepository roleRepository;

    public CommandLineRoleRunner(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        for (Roles roleEnum : Roles.values()) {
            if (!roleRepository.existsByName(roleEnum)) {
                roleRepository.save(new Role(roleEnum));
            }
        }
    }
}
