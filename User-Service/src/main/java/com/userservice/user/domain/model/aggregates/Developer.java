package com.userservice.user.domain.model.aggregates;

import com.userservice.user.domain.model.commands.CreateDeveloperCommand;
import com.userservice.user.domain.model.commands.UpdateDeveloperCommand;
import com.userservice.user.domain.model.valueobjects.developer.*;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Developer {
    @EmbeddedId
    private DeveloperId developerId;

    @Embedded
    private DeveloperFirstName developerFirstName;

    @Embedded
    private DeveloperLastName developerLastName;

    @Embedded
    private DeveloperEmail developerEmail;

    @Embedded
    private DeveloperDescription developerDescription;

    @Embedded
    private DeveloperPhone developerPhone;

    @Embedded
    private DeveloperCountry developerCountry;

    @Embedded
    private DeveloperCompletedProjects developerCompletedProjects;

    @Embedded
    private DeveloperSpecialties developerSpecialties;

    @Embedded
    private DeveloperProfileImgUrl developerProfileImgUrl;

    public Developer() { }

    public Developer(CreateDeveloperCommand createDeveloperCommand) {
        this.developerId = new DeveloperId(createDeveloperCommand.developerId());
        this.developerEmail = new DeveloperEmail(createDeveloperCommand.developerEmail());
        this.developerFirstName = new DeveloperFirstName(createDeveloperCommand.developerFirstName());
        this.developerLastName = new DeveloperLastName(createDeveloperCommand.developerLastName());
    }

    public Developer updateInformation(UpdateDeveloperCommand updateDeveloperCommand) {
        this.developerFirstName = updateDeveloperCommand.developerFirstName();
        this.developerLastName = updateDeveloperCommand.developerLastName();
        //this.developerEmail = updateDeveloperCommand.developerEmail();
        this.developerDescription = updateDeveloperCommand.developerDescription();
        this.developerPhone = updateDeveloperCommand.developerPhone();
        this.developerCountry = updateDeveloperCommand.developerCountry();
        this.developerCompletedProjects = updateDeveloperCommand.developerCompletedProjects();
        this.developerSpecialties = updateDeveloperCommand.developerSpecialties();
        this.developerProfileImgUrl = updateDeveloperCommand.developerProfileImgUrl();
        return this;
    }
}
