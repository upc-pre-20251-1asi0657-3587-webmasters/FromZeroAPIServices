package com.userservice.user.application.internal.commandservices;

import com.userservice.user.domain.model.aggregates.Enterprise;
import com.userservice.user.domain.model.commands.CreateEnterpriseCommand;
import com.userservice.user.domain.services.EnterpriseCommandService;
import com.userservice.user.infrastructure.persistence.jpa.repositories.EnterpriseRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EnterpriseCommandServiceImpl implements EnterpriseCommandService {
    private final EnterpriseRepository enterpriseRepository;

    public EnterpriseCommandServiceImpl(EnterpriseRepository enterpriseRepository) {
        this.enterpriseRepository = enterpriseRepository;
    }

    @Override
    public Optional<Enterprise> handle(CreateEnterpriseCommand createEnterpriseCommand) {
        var enterprise = new Enterprise(createEnterpriseCommand);
        if (enterpriseRepository.existsByEnterpriseEmail(enterprise.getEnterpriseEmail())) throw new IllegalArgumentException("Enterprise email already exists");
        enterpriseRepository.save(enterprise);
        return enterpriseRepository.findByEnterpriseId(enterprise.getEnterpriseId());
    }
}
