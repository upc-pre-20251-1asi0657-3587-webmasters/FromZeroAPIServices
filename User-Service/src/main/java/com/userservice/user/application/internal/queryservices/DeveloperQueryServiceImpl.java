package com.userservice.user.application.internal.queryservices;

import com.userservice.user.domain.model.aggregates.Developer;
import com.userservice.user.domain.model.queries.GetDeveloperByIdQuery;
import com.userservice.user.domain.services.DeveloperQueryService;
import com.userservice.user.infrastructure.persistence.jpa.repositories.DeveloperRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeveloperQueryServiceImpl implements DeveloperQueryService {
    private final DeveloperRepository developerRepository;

    public DeveloperQueryServiceImpl(DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }

    @Override
    public List<Developer> handle() {
        return developerRepository.findAll();
    }

    @Override
    public Optional<Developer> handle(GetDeveloperByIdQuery getDeveloperByIdQuery) {
        return developerRepository.findByDeveloperId(getDeveloperByIdQuery.developerId());
    }
}
