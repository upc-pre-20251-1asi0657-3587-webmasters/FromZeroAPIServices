package com.userservice.user.interfaces.rest.transform.enterprise;

import com.userservice.user.domain.model.commands.CreateEnterpriseCommand;
import com.userservice.user.interfaces.rest.resources.CreateEnterpriseResource;

public class CreateEnterpriseCommandFromResourceAssembler {
    public static CreateEnterpriseCommand toCommandFromResource(CreateEnterpriseResource createEnterpriseResource) {
        return new CreateEnterpriseCommand(createEnterpriseResource.enterpriseId(), createEnterpriseResource.enterpriseEmail(), createEnterpriseResource.enterpriseName());
    }
}
