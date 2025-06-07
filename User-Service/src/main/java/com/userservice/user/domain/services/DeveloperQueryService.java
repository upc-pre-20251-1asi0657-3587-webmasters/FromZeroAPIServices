package com.userservice.user.domain.services;

import com.userservice.user.domain.model.aggregates.Developer;
import com.userservice.user.domain.model.queries.GetDeveloperByIdQuery;

import java.util.List;
import java.util.Optional;

public interface DeveloperQueryService {
    List<Developer> handle();
    Optional<Developer> handle(GetDeveloperByIdQuery getDeveloperByIdQuery);
}
