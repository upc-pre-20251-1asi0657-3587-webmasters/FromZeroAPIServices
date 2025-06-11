package com.userservice.user.application.internal.commandservices;

import com.userservice.user.domain.model.aggregates.Enterprise;
import com.userservice.user.domain.model.commands.CreateEnterpriseCommand;
import com.userservice.user.domain.model.commands.UpdateEnterpriseCommand;
import com.userservice.user.domain.model.events.UserCreatedEvent;
import com.userservice.user.domain.services.EnterpriseCommandService;
import com.userservice.user.infrastructure.eventpublisher.UserPublisher;
import com.userservice.user.infrastructure.persistence.jpa.repositories.EnterpriseRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EnterpriseCommandServiceImpl implements EnterpriseCommandService {
    private final EnterpriseRepository enterpriseRepository;
    private final UserPublisher userPublisher;

    public EnterpriseCommandServiceImpl(EnterpriseRepository enterpriseRepository, UserPublisher userPublisher) {
        this.enterpriseRepository = enterpriseRepository;
        this.userPublisher = userPublisher;
    }

    @Override
    public Optional<Enterprise> handle(CreateEnterpriseCommand createEnterpriseCommand) {
        var enterprise = new Enterprise(createEnterpriseCommand);
        if (enterpriseRepository.existsByEnterpriseEmail(enterprise.getEnterpriseEmail())) throw new IllegalArgumentException("Enterprise email already exists");
        enterpriseRepository.save(enterprise);

        UserCreatedEvent event = new UserCreatedEvent();
        event.setName(enterprise.getEnterpriseName().toString());
        event.setEmail(enterprise.getEnterpriseEmail().toString());
        event.setRole("Enterprise");
        userPublisher.publishUserCreatedevent(event);

        return enterpriseRepository.findByEnterpriseId(enterprise.getEnterpriseId());
    }

    @Override
    public Optional<Enterprise> handle(UpdateEnterpriseCommand updateEnterpriseCommand) {
        var enterprise = enterpriseRepository.findByEnterpriseId(updateEnterpriseCommand.enterpriseId());
        if (enterprise.isEmpty()) throw new IllegalArgumentException("Enterprise does not exist");
        var enterpriseToUpdate = enterprise.get();
        try {
            var updatedEnterprise = enterpriseRepository.save(enterpriseToUpdate.updateInformation(updateEnterpriseCommand));
            return Optional.of(updatedEnterprise);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error updating enterprise");
        }
    }
}
