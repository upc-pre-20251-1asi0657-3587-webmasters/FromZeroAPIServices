package com.example.projectservice.projects.domain.services;



import com.example.projectservice.projects.domain.model.aggregates.Framework;
import com.example.projectservice.projects.domain.model.queries.GetFrameworkByIdQuery;

import java.util.Optional;

public interface FrameworksQueryService {
    Optional<Framework> handle(GetFrameworkByIdQuery query);
}
