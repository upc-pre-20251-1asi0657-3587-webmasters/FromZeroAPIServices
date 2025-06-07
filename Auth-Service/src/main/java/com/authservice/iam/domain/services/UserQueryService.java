package com.authservice.iam.domain.services;

import com.authservice.iam.domain.model.aggregates.User;
import com.authservice.iam.domain.model.queries.GetUserByIdQuery;

import java.util.List;
import java.util.Optional;

public interface UserQueryService {
    List<User> handle();
    Optional<User> handle(GetUserByIdQuery getUserByIdQuery);
}
