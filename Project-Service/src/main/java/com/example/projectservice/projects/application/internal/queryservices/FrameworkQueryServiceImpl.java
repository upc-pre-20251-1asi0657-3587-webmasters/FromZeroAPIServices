package com.example.projectservice.projects.application.internal.queryservices;


import com.example.projectservice.projects.domain.model.aggregates.Framework;
import com.example.projectservice.projects.domain.model.queries.GetFrameworkByIdQuery;
import com.example.projectservice.projects.domain.services.FrameworksQueryService;
import com.example.projectservice.projects.infrastructure.persistence.jpa.repositories.FrameworksRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FrameworkQueryServiceImpl implements FrameworksQueryService {

    private final FrameworksRepository frameworksRepository;

    public FrameworkQueryServiceImpl(FrameworksRepository frameworksRepository) {
        this.frameworksRepository = frameworksRepository;
    }

    @Override
    public Optional<Framework> handle(GetFrameworkByIdQuery query) {
        return this.frameworksRepository.findFrameworkById(query.id());
    }
}
