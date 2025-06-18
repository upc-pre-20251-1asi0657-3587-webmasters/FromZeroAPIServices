package com.userservice.user.application.internal.commandservices;

import com.userservice.user.domain.model.aggregates.Developer;
import com.userservice.user.domain.model.commands.CreateDeveloperCommand;
import com.userservice.user.domain.model.commands.UpdateDeveloperCommand;
import com.userservice.user.domain.model.events.UserCreatedEvent;
import com.userservice.user.domain.services.DeveloperCommandService;
import com.userservice.user.infrastructure.eventpublisher.UserPublisher;
import com.userservice.user.infrastructure.persistence.jpa.repositories.DeveloperRepository;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DeveloperCommandServiceImpl implements DeveloperCommandService {
    private final DeveloperRepository developerRepository;
    private final UserPublisher userPublisher;

    public DeveloperCommandServiceImpl(DeveloperRepository developerRepository, UserPublisher userPublisher) {
        this.developerRepository = developerRepository;
        this.userPublisher = userPublisher;
    }

    @Override
    public Optional<Developer> handle(CreateDeveloperCommand createDeveloperCommand) {
        var developer = new Developer(createDeveloperCommand);
        if (developerRepository.existsByDeveloperEmail(developer.getDeveloperEmail())) throw new IllegalArgumentException("Developer email already exists");
        developerRepository.save(developer);


        UserCreatedEvent event = new UserCreatedEvent();
        event.setUserId(developer.getDeveloperId().developerId());
        event.setName(developer.getDeveloperFirstName().developerFirstName());
        event.setEmail(developer.getDeveloperEmail().developerEmail());
        event.setRole("Developer");
        userPublisher.publishUserCreatedevent(event);


        return developerRepository.findByDeveloperId(developer.getDeveloperId());
    }

    @Override
    public Optional<Developer> handle(UpdateDeveloperCommand updateDeveloperCommand) {
        var developer = developerRepository.findByDeveloperId(updateDeveloperCommand.developerId());
        if (developer.isEmpty()) throw new IllegalArgumentException("Developer does not exist");
        var developerToUpdate = developer.get();
        try {
            var updatedDeveloper = developerRepository.save(developerToUpdate.updateInformation(updateDeveloperCommand));
            return Optional.of(updatedDeveloper);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error updating developer");
        }
    }
}
