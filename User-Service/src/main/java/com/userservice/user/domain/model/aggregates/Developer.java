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

    public Developer() { }

    public Developer(CreateDeveloperCommand createDeveloperCommand) {
        this.developerId = new DeveloperId(createDeveloperCommand.developerId());
        this.developerEmail = new DeveloperEmail(createDeveloperCommand.developerEmail());
    }

    public Developer updateInformation(UpdateDeveloperCommand updateDeveloperCommand) {
        this.developerFirstName = updateDeveloperCommand.developerFirstName();
        this.developerLastName = updateDeveloperCommand.developerLastName();
        //this.developerEmail = updateDeveloperCommand.developerEmail();
        this.developerDescription = updateDeveloperCommand.developerDescription();
        this.developerPhone = updateDeveloperCommand.developerPhone();
        this.developerCountry = updateDeveloperCommand.developerCountry();
        return this;
    }
}
