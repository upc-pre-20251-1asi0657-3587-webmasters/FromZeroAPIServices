package com.authservice.iam.interfaces.rest.transform;

import com.authservice.iam.domain.model.commands.SignUpEnterpriseCommand;
import com.authservice.iam.interfaces.rest.resources.SignUpEnterpriseResource;

public class SignUpEnterpriseCommandFromResourceAssembler {
    public static SignUpEnterpriseCommand toCommandFromResource(SignUpEnterpriseResource signUpResource) {
        return new SignUpEnterpriseCommand(signUpResource.userEmail(), signUpResource.userPassword(), signUpResource.userRole());
    }
}
