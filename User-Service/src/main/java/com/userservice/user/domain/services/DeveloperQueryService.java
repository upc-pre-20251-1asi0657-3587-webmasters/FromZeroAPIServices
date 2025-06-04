package com.userservice.user.domain.services;

import com.userservice.user.domain.model.aggregates.Developer;
import com.userservice.user.domain.model.queries.GetDeveloperByIdQuery;

import java.util.Optional;

public interface DeveloperQueryService {
    Optional<Developer> handle(GetDeveloperByIdQuery getDeveloperByIdQuery);
}
