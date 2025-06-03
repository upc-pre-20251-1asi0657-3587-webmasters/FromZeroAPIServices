package com.authservice.iam.domain.services;

import com.authservice.iam.domain.model.aggregates.User;
import com.authservice.iam.domain.model.commands.SignInCommand;
import com.authservice.iam.domain.model.commands.SignUpCommand;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Optional;

public interface UserCommandService {
    Optional<User> handle(SignUpCommand signUpCommand);
    Optional<ImmutablePair<User, String>> handle(SignInCommand signInCommand);
}
