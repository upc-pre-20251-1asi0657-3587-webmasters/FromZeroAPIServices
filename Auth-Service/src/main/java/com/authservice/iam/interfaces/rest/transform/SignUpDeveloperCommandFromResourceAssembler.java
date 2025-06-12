package com.authservice.iam.interfaces.rest.transform;

import com.authservice.iam.domain.model.commands.SignUpDeveloperCommand;
import com.authservice.iam.interfaces.rest.resources.SignUpDeveloperResource;

public class SignUpDeveloperCommandFromResourceAssembler {
    public static SignUpDeveloperCommand toCommandFromResource(SignUpDeveloperResource signUpResource) {
        return new SignUpDeveloperCommand(signUpResource.userEmail(), signUpResource.password(), "ROLE_DEVELOPER");
    }
}
