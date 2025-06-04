package com.userservice.user.domain.model.aggregates;

import com.userservice.user.domain.model.commands.CreateEnterpriseCommand;
import com.userservice.user.domain.model.commands.UpdateEnterpriseCommand;
import com.userservice.user.domain.model.valueobjects.enterprise.*;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Enterprise {
    @EmbeddedId
    private EnterpriseId enterpriseId;

    @Embedded
    private EnterpriseName enterpriseName;

    @Embedded
    private EnterpriseEmail enterpriseEmail;

    @Embedded
    private EnterpriseDescription enterpriseDescription;

    @Embedded
    private EnterpriseCountry enterpriseCountry;

    @Embedded
    private EnterpriseRuc enterpriseRuc;

    @Embedded
    private EnterprisePhone enterprisePhone;

    @Embedded
    private EnterpriseWebsite enterpriseWebsite;

    @Embedded
    private EnterpriseProfileImgUrl enterpriseProfileImgUrl;

    @Embedded
    private EnterpriseSector enterpriseSector;

    public Enterprise() { }

    public Enterprise(CreateEnterpriseCommand createEnterpriseCommand) {
        this.enterpriseId = new EnterpriseId(createEnterpriseCommand.enterpriseId());
        this.enterpriseEmail = new EnterpriseEmail(createEnterpriseCommand.enterpriseEmail());
    }

    public Enterprise updateInformation(UpdateEnterpriseCommand updateEnterpriseCommand) {
        this.enterpriseName = updateEnterpriseCommand.enterpriseName();
        //this.enterpriseEmail = updateEnterpriseCommand.enterpriseEmail();
        this.enterpriseDescription = updateEnterpriseCommand.enterpriseDescription();
        this.enterpriseCountry = updateEnterpriseCommand.enterpriseCountry();
        this.enterpriseRuc = updateEnterpriseCommand.enterpriseRuc();
        this.enterprisePhone = updateEnterpriseCommand.enterprisePhone();
        this.enterpriseWebsite = updateEnterpriseCommand.enterpriseWebsite();
        this.enterpriseProfileImgUrl = updateEnterpriseCommand.enterpriseProfileImgUrl();
        this.enterpriseSector = updateEnterpriseCommand.enterpriseSector();
        return this;
    }
}
