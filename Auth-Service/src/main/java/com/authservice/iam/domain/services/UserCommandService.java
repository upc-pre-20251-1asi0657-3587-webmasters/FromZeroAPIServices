package com.authservice.iam.domain.services;

import com.authservice.iam.domain.model.aggregates.User;
import com.authservice.iam.domain.model.commands.SignInCommand;
import com.authservice.iam.domain.model.commands.SignUpDeveloperCommand;
import com.authservice.iam.domain.model.commands.SignUpEnterpriseCommand;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Optional;

public interface UserCommandService {
    Optional<User> handle(SignUpDeveloperCommand signUpDeveloperCommand);
    Optional<User> handle(SignUpEnterpriseCommand signUpEnterpriseCommand);
    Optional<ImmutablePair<User, String>> handle(SignInCommand signInCommand);
}
