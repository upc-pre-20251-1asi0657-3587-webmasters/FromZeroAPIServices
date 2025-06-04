package com.userservice.user.domain.model.aggregates;

import com.userservice.user.domain.model.valueobjects.enterprise.*;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
@Getter
public class Enterprise {
    @EmbeddedId
    private EnterpriseId enterpriseId;

    @Embedded
    private EnterpriseName enterpriseName;

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
}
