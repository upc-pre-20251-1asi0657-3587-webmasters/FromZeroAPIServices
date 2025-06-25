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
        this.developerDescription = new DeveloperDescription();
        this.developerPhone = new DeveloperPhone();
        this.developerCountry = new DeveloperCountry();
        this.developerCompletedProjects = new DeveloperCompletedProjects();
        this.developerSpecialties = new DeveloperSpecialties();
        this.developerProfileImgUrl = new DeveloperProfileImgUrl();
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

    public DeveloperId getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(DeveloperId developerId) {
        this.developerId = developerId;
    }

    public DeveloperFirstName getDeveloperFirstName() {
        return developerFirstName;
    }

    public void setDeveloperFirstName(DeveloperFirstName developerFirstName) {
        this.developerFirstName = developerFirstName;
    }

    public DeveloperLastName getDeveloperLastName() {
        return developerLastName;
    }

    public void setDeveloperLastName(DeveloperLastName developerLastName) {
        this.developerLastName = developerLastName;
    }

    public DeveloperEmail getDeveloperEmail() {
        return developerEmail;
    }

    public void setDeveloperEmail(DeveloperEmail developerEmail) {
        this.developerEmail = developerEmail;
    }

    public DeveloperDescription getDeveloperDescription() {
        return developerDescription;
    }

    public void setDeveloperDescription(DeveloperDescription developerDescription) {
        this.developerDescription = developerDescription;
    }

    public DeveloperPhone getDeveloperPhone() {
        return developerPhone;
    }

    public void setDeveloperPhone(DeveloperPhone developerPhone) {
        this.developerPhone = developerPhone;
    }

    public DeveloperCountry getDeveloperCountry() {
        return developerCountry;
    }

    public void setDeveloperCountry(DeveloperCountry developerCountry) {
        this.developerCountry = developerCountry;
    }

    public DeveloperCompletedProjects getDeveloperCompletedProjects() {
        return developerCompletedProjects;
    }

    public void setDeveloperCompletedProjects(DeveloperCompletedProjects developerCompletedProjects) {
        this.developerCompletedProjects = developerCompletedProjects;
    }

    public DeveloperSpecialties getDeveloperSpecialties() {
        return developerSpecialties;
    }

    public void setDeveloperSpecialties(DeveloperSpecialties developerSpecialties) {
        this.developerSpecialties = developerSpecialties;
    }

    public DeveloperProfileImgUrl getDeveloperProfileImgUrl() {
        return developerProfileImgUrl;
    }

    public void setDeveloperProfileImgUrl(DeveloperProfileImgUrl developerProfileImgUrl) {
        this.developerProfileImgUrl = developerProfileImgUrl;
    }
}
