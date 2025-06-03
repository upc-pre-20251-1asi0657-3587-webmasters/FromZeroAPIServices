package com.userservice.user.interfaces.rest.transform;

import com.userservice.user.domain.model.commands.UpdateDeveloperCommand;
import com.userservice.user.domain.model.valueobjects.*;
import com.userservice.user.interfaces.rest.resources.UpdateDeveloperResource;

import java.util.UUID;

public class UpdateDeveloperCommandFromResourceAssembler {
    public static UpdateDeveloperCommand toCommandFromResource(UUID developerId, UpdateDeveloperResource updateDeveloperResource) {
        return new UpdateDeveloperCommand(new DeveloperId(developerId), new DeveloperFirstName(updateDeveloperResource.developerFirstName()), new DeveloperLastName(updateDeveloperResource.developerLastName()), new DeveloperEmail(updateDeveloperResource.developerEmail()), new DeveloperDescription(updateDeveloperResource.developerDescription()), new DeveloperPhone(updateDeveloperResource.developerPhone()), new DeveloperCountry(updateDeveloperResource.developerCountry()));
    }
}
