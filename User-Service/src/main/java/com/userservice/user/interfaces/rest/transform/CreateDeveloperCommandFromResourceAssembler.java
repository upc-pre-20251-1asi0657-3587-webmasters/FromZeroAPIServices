package com.userservice.user.interfaces.rest.transform;

import com.userservice.user.domain.model.commands.CreateDeveloperCommand;
import com.userservice.user.interfaces.rest.resources.CreateDeveloperResource;

public class CreateDeveloperCommandFromResourceAssembler {
    public static CreateDeveloperCommand toCommandFromResource(CreateDeveloperResource createDeveloperResource) {
        return new CreateDeveloperCommand(createDeveloperResource.developerId(), createDeveloperResource.developerEmail());
    }
}
