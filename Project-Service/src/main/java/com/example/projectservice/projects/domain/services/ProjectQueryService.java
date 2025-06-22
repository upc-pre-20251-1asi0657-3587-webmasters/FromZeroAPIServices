package com.example.projectservice.projects.domain.services;



import com.example.projectservice.projects.domain.model.aggregates.Project;
import com.example.projectservice.projects.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface ProjectQueryService {
    List<Project> handle(GetAllProjectsQuery query);
    List<Project>handle(GetAllProjectsByStateQuery query);
    Optional<Project> handle(GetProjectByIdQuery query);
    List<Project> handle(GetAllProjectsByDeveloperIdQuery query);
    List<Project> handle(GetAllProjectsByEnterpriseIdQuery query);
}
