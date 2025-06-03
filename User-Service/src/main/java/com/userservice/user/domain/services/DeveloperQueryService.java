package com.userservice.user.domain.services;

import com.userservice.user.domain.model.aggregates.Developer;
import com.userservice.user.domain.model.queries.GetDeveloperByIdQuery;
import com.userservice.user.domain.model.valueobjects.DeveloperId;

import java.util.Optional;

public interface DeveloperQueryService {
    Optional<Developer> handle(GetDeveloperByIdQuery getDeveloperByIdQuery);
}
