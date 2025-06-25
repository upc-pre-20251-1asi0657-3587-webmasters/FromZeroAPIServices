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
        this.enterpriseName = new EnterpriseName(createEnterpriseCommand.enterpriseName());
        this.enterpriseDescription = new EnterpriseDescription();
        this.enterpriseCountry = new EnterpriseCountry();
        this.enterpriseRuc = new EnterpriseRuc();
        this.enterprisePhone = new EnterprisePhone();
        this.enterpriseWebsite = new EnterpriseWebsite();
        this.enterpriseProfileImgUrl = new EnterpriseProfileImgUrl();
        this.enterpriseSector = new EnterpriseSector();
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

    public EnterpriseId getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(EnterpriseId enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public EnterpriseName getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(EnterpriseName enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public EnterpriseEmail getEnterpriseEmail() {
        return enterpriseEmail;
    }

    public void setEnterpriseEmail(EnterpriseEmail enterpriseEmail) {
        this.enterpriseEmail = enterpriseEmail;
    }

    public EnterpriseDescription getEnterpriseDescription() {
        return enterpriseDescription;
    }

    public void setEnterpriseDescription(EnterpriseDescription enterpriseDescription) {
        this.enterpriseDescription = enterpriseDescription;
    }

    public EnterpriseCountry getEnterpriseCountry() {
        return enterpriseCountry;
    }

    public void setEnterpriseCountry(EnterpriseCountry enterpriseCountry) {
        this.enterpriseCountry = enterpriseCountry;
    }

    public EnterpriseRuc getEnterpriseRuc() {
        return enterpriseRuc;
    }

    public void setEnterpriseRuc(EnterpriseRuc enterpriseRuc) {
        this.enterpriseRuc = enterpriseRuc;
    }

    public EnterprisePhone getEnterprisePhone() {
        return enterprisePhone;
    }

    public void setEnterprisePhone(EnterprisePhone enterprisePhone) {
        this.enterprisePhone = enterprisePhone;
    }

    public EnterpriseWebsite getEnterpriseWebsite() {
        return enterpriseWebsite;
    }

    public void setEnterpriseWebsite(EnterpriseWebsite enterpriseWebsite) {
        this.enterpriseWebsite = enterpriseWebsite;
    }

    public EnterpriseProfileImgUrl getEnterpriseProfileImgUrl() {
        return enterpriseProfileImgUrl;
    }

    public void setEnterpriseProfileImgUrl(EnterpriseProfileImgUrl enterpriseProfileImgUrl) {
        this.enterpriseProfileImgUrl = enterpriseProfileImgUrl;
    }

    public EnterpriseSector getEnterpriseSector() {
        return enterpriseSector;
    }

    public void setEnterpriseSector(EnterpriseSector enterpriseSector) {
        this.enterpriseSector = enterpriseSector;
    }
}
