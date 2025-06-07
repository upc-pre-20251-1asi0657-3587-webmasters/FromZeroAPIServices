package com.userservice.user.interfaces.rest.transform.enterprise;

import com.userservice.user.domain.model.commands.UpdateEnterpriseCommand;
import com.userservice.user.domain.model.valueobjects.enterprise.*;
import com.userservice.user.interfaces.rest.resources.UpdateEnterpriseResource;

import java.util.UUID;

public class UpdateEnterpriseCommandFromResourceAssembler {
    public static UpdateEnterpriseCommand toCommandFromResource(UUID developerId, UpdateEnterpriseResource updateEnterpriseResource) {
        return new UpdateEnterpriseCommand(new EnterpriseId(developerId), new EnterpriseName(updateEnterpriseResource.enterpriseName()), new EnterpriseEmail(updateEnterpriseResource.enterpriseEmail()), new EnterpriseDescription(updateEnterpriseResource.enterpriseDescription()), new EnterpriseCountry(updateEnterpriseResource.enterpriseCountry()), new EnterpriseRuc(updateEnterpriseResource.enterpriseRuc()), new EnterprisePhone(updateEnterpriseResource.enterprisePhone()), new EnterpriseWebsite(updateEnterpriseResource.enterpriseWebsite()), new EnterpriseProfileImgUrl(updateEnterpriseResource.enterpriseProfileImgUrl()), new EnterpriseSector(updateEnterpriseResource.enterpriseSector()));
    }
}
