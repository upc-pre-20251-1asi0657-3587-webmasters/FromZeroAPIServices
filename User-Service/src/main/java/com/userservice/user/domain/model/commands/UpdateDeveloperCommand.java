package com.userservice.user.domain.model.commands;

import com.userservice.user.domain.model.valueobjects.developer.*;

public record UpdateDeveloperCommand(DeveloperId developerId, DeveloperFirstName developerFirstName, DeveloperLastName developerLastName, DeveloperEmail developerEmail, DeveloperDescription developerDescription, DeveloperPhone developerPhone, DeveloperCountry developerCountry, DeveloperCompletedProjects developerCompletedProjects, DeveloperSpecialties developerSpecialties, DeveloperProfileImgUrl developerProfileImgUrl) {
}
