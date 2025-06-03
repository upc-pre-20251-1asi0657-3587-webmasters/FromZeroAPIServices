package com.authservice.iam.application.internal.commandservices;

import com.authservice.iam.application.internal.outboundservices.hashing.HashingService;
import com.authservice.iam.application.internal.outboundservices.tokens.TokenService;
import com.authservice.iam.application.ports.output.UserProfileGateway;
import com.authservice.iam.domain.exceptions.InvalidCredentialsException;
import com.authservice.iam.domain.exceptions.UserAlreadyExistsException;
import com.authservice.iam.domain.model.aggregates.User;
import com.authservice.iam.domain.model.commands.SignInCommand;
import com.authservice.iam.domain.model.commands.SignUpCommand;
import com.authservice.iam.domain.services.UserCommandService;
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

    public UserCommandServiceImpl(UserRepository userRepository, RoleRepository roleRepository, HashingService hashingService, TokenService tokenService, UserProfileGateway userProfileGateway) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.hashingService = hashingService;
        this.tokenService = tokenService;
        this.userProfileGateway = userProfileGateway;
    }

    @Override
    public Optional<User> handle(SignUpCommand signUpCommand) {
        if (userRepository.existsByUserEmail(signUpCommand.userEmail())) {
            throw new UserAlreadyExistsException(signUpCommand.userEmail());
        }

        var encodedPassword = hashingService.encode(signUpCommand.userPassword());

        var user = new User(signUpCommand, encodedPassword);

        userRepository.save(user);

        userProfileGateway.createUserProfile(user.getUserId(), signUpCommand.userEmail());

        return userRepository.findById(user.getUserId());
    }

    @Override
    public Optional<ImmutablePair<User, String>> handle(SignInCommand signInCommand) {
        var user = userRepository.findByUserEmail(signInCommand.userEmail()).orElseThrow(InvalidCredentialsException::new);
        if (!hashingService.matches(signInCommand.userPassword(), user.getUserPassword())) {
            throw new InvalidCredentialsException();
        }
        var token = tokenService.generateToken(user);
        return Optional.of(new ImmutablePair<>(user, token));
    }
}
