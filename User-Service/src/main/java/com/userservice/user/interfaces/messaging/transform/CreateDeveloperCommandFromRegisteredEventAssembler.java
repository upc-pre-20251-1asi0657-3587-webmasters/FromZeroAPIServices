package com.userservice.user.interfaces.messaging.transform;

import com.userservice.user.domain.model.commands.CreateDeveloperCommand;
import com.userservice.user.interfaces.messaging.events.DeveloperRegisteredEvent;

public class CreateDeveloperCommandFromRegisteredEventAssembler {
    public static CreateDeveloperCommand toCommandFromEvent(DeveloperRegisteredEvent developerRegisteredEvent) {
        return new CreateDeveloperCommand(developerRegisteredEvent.uuid(), developerRegisteredEvent.email());
    }
}
