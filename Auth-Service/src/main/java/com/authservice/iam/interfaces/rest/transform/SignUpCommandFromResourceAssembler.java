package com.authservice.iam.interfaces.rest.transform;

import com.authservice.iam.domain.model.commands.SignUpCommand;
import com.authservice.iam.interfaces.rest.resources.SignUpResource;

public class SignUpCommandFromResourceAssembler {
    public static SignUpCommand toCommandFromResource(SignUpResource signUpResource) {
        return new SignUpCommand(signUpResource.userEmail(), signUpResource.userPassword(), signUpResource.userRole());
    }
}
