package com.authservice.iam.interfaces.rest.transform;

import com.authservice.iam.domain.model.commands.SignInCommand;
import com.authservice.iam.interfaces.rest.resources.SignInResource;

public class SignInCommandFromResourceAssembler {
    public static SignInCommand toCommandFromResource(SignInResource signInResource) {
        return new SignInCommand(signInResource.userEmail(), signInResource.password());
    }
}
