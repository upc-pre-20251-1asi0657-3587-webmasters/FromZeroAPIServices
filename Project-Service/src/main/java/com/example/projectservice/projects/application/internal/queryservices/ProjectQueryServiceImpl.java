package com.example.projectservice.projects.application.internal.queryservices;


import com.example.projectservice.projects.domain.model.aggregates.Project;
import com.example.projectservice.projects.domain.model.queries.*;
import com.example.projectservice.projects.domain.services.ProjectQueryService;
import com.example.projectservice.projects.infrastructure.persistence.jpa.repositories.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectQueryServiceImpl implements ProjectQueryService {
    private final ProjectRepository projectRepository;
    public ProjectQueryServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public List<Project> handle(GetAllProjectsQuery query) {
        return this.projectRepository.findAll();
    }

    @Override
    public List<Project> handle(GetAllProjectsByStateQuery query) {
        return this.projectRepository.findAllByState(query.state());
    }

    @Override
    public Optional<Project> handle(GetProjectByIdQuery query) {
        return this.projectRepository.findById(query.id());
    }

    @Override
    public List<Project> handle(GetAllProjectsByDeveloperIdQuery query) {
        return this.projectRepository.findAllByDeveloperId(query.developerId());
    }

    @Override
    public List<Project> handle(GetAllProjectsByEnterpriseIdQuery query) {
        return this.projectRepository.findAllByEnterprise(query.enterprise());
    }
}
