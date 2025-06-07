package com.authservice.iam.application.internal.queryservices;

import com.authservice.iam.domain.model.aggregates.User;
import com.authservice.iam.domain.model.queries.GetUserByIdQuery;
import com.authservice.iam.domain.services.UserQueryService;
import com.authservice.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserQueryServiceImpl implements UserQueryService {
    private final UserRepository userRepository;

    public UserQueryServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> handle() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> handle(GetUserByIdQuery getUserByIdQuery) {
        return userRepository.findById(getUserByIdQuery.userId());
    }
}
