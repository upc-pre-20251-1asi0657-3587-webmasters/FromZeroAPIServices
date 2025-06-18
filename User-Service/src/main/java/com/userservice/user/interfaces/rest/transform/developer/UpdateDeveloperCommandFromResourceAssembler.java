package com.userservice.user.interfaces.rest.transform.developer;

import com.userservice.user.domain.model.commands.UpdateDeveloperCommand;
import com.userservice.user.domain.model.valueobjects.developer.*;
import com.userservice.user.interfaces.rest.resources.UpdateDeveloperResource;

import java.util.UUID;

public class UpdateDeveloperCommandFromResourceAssembler {
    public static UpdateDeveloperCommand toCommandFromResource(UUID developerId, UpdateDeveloperResource updateDeveloperResource) {
        return new UpdateDeveloperCommand(new DeveloperId(developerId), new DeveloperFirstName(updateDeveloperResource.firstName()), new DeveloperLastName(updateDeveloperResource.lastName()), new DeveloperEmail(updateDeveloperResource.developerEmail()), new DeveloperDescription(updateDeveloperResource.description()), new DeveloperPhone(updateDeveloperResource.phone()), new DeveloperCountry(updateDeveloperResource.country()), new DeveloperCompletedProjects(0), new DeveloperSpecialties(updateDeveloperResource.specialties()), new DeveloperProfileImgUrl(updateDeveloperResource.profileImgUrl()));
    }
}
