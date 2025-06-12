package com.userservice.user.interfaces.rest.transform.developer;

import com.userservice.user.domain.model.aggregates.Developer;
import com.userservice.user.interfaces.rest.resources.DeveloperResource;

public class DeveloperResourceFromEntityAssembler {
    public static DeveloperResource toResourceFromEntity(Developer entity) {
        return new DeveloperResource(entity.getDeveloperId().developerId().toString(), entity.getDeveloperFirstName().developerFirstName(), entity.getDeveloperLastName().developerLastName(), entity.getDeveloperEmail().developerEmail(), entity.getDeveloperDescription().developerDescription(), entity.getDeveloperCountry().developerCountry(), entity.getDeveloperPhone().developerPhone(), entity.getDeveloperSpecialties().developerSpecialties(), entity.getDeveloperProfileImgUrl().developerProfileImgUrl());
    }
}
