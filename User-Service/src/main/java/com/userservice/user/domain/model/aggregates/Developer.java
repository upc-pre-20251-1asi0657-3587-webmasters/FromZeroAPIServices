package com.userservice.user.domain.model.aggregates;

import com.userservice.user.domain.model.commands.CreateDeveloperCommand;
import com.userservice.user.domain.model.valueobjects.DeveloperEmail;
import com.userservice.user.domain.model.valueobjects.DeveloperId;
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
    private DeveloperEmail developerEmail;

    public Developer() { }

    public Developer(CreateDeveloperCommand createDeveloperCommand) {
        this.developerId = new DeveloperId(createDeveloperCommand.developerId());
        this.developerEmail = new DeveloperEmail(createDeveloperCommand.developerEmail());
    }
}
