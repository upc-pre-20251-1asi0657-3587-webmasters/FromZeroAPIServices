package com.userservice.user.interfaces.rest.transform.enterprise;

import com.userservice.user.domain.model.aggregates.Enterprise;
import com.userservice.user.interfaces.rest.resources.EnterpriseResource;

public class EnterpriseResourceFromEntityAssembler {
    public static EnterpriseResource toResourceFromEntity(Enterprise entity) {
        return new EnterpriseResource(entity.getEnterpriseName().enterpriseName(), entity.getEnterpriseEmail().enterpriseEmail(), entity.getEnterpriseDescription().enterpriseDescription(), entity.getEnterpriseCountry().enterpriseCountry(), entity.getEnterpriseRuc().enterpriseRuc(), entity.getEnterprisePhone().enterprisePhone(), entity.getEnterpriseWebsite().enterpriseWebsite(), entity.getEnterpriseProfileImgUrl().enterpriseProfileImgUrl(), entity.getEnterpriseSector().enterpriseSector());
    }
}
