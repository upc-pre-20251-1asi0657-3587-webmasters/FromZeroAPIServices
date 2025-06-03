package com.userservice.user.domain.services;

import com.userservice.user.domain.model.aggregates.Developer;
import com.userservice.user.domain.model.commands.CreateDeveloperCommand;
import com.userservice.user.domain.model.commands.UpdateDeveloperCommand;

import java.util.Optional;

public interface DeveloperCommandService {
    Optional<Developer> handle(CreateDeveloperCommand createDeveloperCommand);
    Optional<Developer> handle(UpdateDeveloperCommand updateDeveloperCommand);
}
