package com.authservice.iam.application.internal.commandservices;

import com.authservice.iam.application.internal.outboundservices.hashing.HashingService;
import com.authservice.iam.application.internal.outboundservices.tokens.TokenService;
import com.authservice.iam.application.ports.output.UserProfileGateway;
import com.authservice.iam.domain.events.AccountCreatedEvent;
import com.authservice.iam.domain.exceptions.InvalidCredentialsException;
import com.authservice.iam.domain.exceptions.UserAlreadyExistsException;
import com.authservice.iam.domain.model.aggregates.User;
import com.authservice.iam.domain.model.commands.RefreshTokenCommand;
import com.authservice.iam.domain.model.commands.SignInCommand;
import com.authservice.iam.domain.model.commands.SignUpDeveloperCommand;
import com.authservice.iam.domain.model.commands.SignUpEnterpriseCommand;
import com.authservice.iam.domain.services.UserCommandService;
import com.authservice.iam.infrastructure.eventpublisher.AuthPublisher;
import com.authservice.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import com.authservice.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserCommandServiceImpl implements UserCommandService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final HashingService hashingService;
    private final TokenService tokenService;

    private final UserProfileGateway userProfileGateway;

    public UserCommandServiceImpl(UserRepository userRepository, RoleRepository roleRepository, HashingService hashingService, TokenService tokenService, AuthPublisher authPublisher, UserProfileGateway userProfileGateway) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.hashingService = hashingService;
        this.tokenService = tokenService;
        this.userProfileGateway = userProfileGateway;
    }

    @Override
    public Optional<User> handle(SignUpDeveloperCommand signUpDeveloperCommand) {
        if (userRepository.existsByUserEmail(signUpDeveloperCommand.userEmail())) {
            throw new UserAlreadyExistsException(signUpDeveloperCommand.userEmail());
        }

        var encodedPassword = hashingService.encode(signUpDeveloperCommand.userPassword());

        var user = new User(signUpDeveloperCommand, encodedPassword);

        userRepository.save(user);

        userProfileGateway.createDeveloperProfile(user.getUserId(), user.getUserEmail());

        return userRepository.findById(user.getUserId());
    }

    @Override
    public Optional<User> handle(SignUpEnterpriseCommand signUpEnterpriseCommand) {
        if (userRepository.existsByUserEmail(signUpEnterpriseCommand.userEmail())) {
            throw new UserAlreadyExistsException(signUpEnterpriseCommand.userEmail());
        }

        var encodedPassword = hashingService.encode(signUpEnterpriseCommand.userPassword());

        var user = new User(signUpEnterpriseCommand, encodedPassword);

        userRepository.save(user);

        userProfileGateway.createEnterpriseProfile(user.getUserId(), user.getUserEmail());


        return userRepository.findById(user.getUserId());
    }

    @Override
    public Optional<ImmutablePair<User, String>> handle(SignInCommand signInCommand) {
        var user = userRepository.findByUserEmail(signInCommand.userEmail()).orElseThrow(InvalidCredentialsException::new);
        if (!hashingService.matches(signInCommand.userPassword(), user.getUserPassword())) {
            throw new InvalidCredentialsException();
        }
        var token = tokenService.generateToken(user.getUserEmail());
        return Optional.of(new ImmutablePair<>(user, token));
    }

    @Override
    public Optional<ImmutablePair<User, String>> handle(RefreshTokenCommand command) {
        String username = tokenService.getUsernameFromToken(command.token());
        var user = userRepository.findByUserEmail(username);
        if (user.isEmpty()) throw new RuntimeException("User not found");
        var token = tokenService.generateToken(user.get().getUserEmail());
        return Optional.of(ImmutablePair.of(user.get(), token));
    }
}
