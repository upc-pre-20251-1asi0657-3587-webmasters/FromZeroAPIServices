package com.example.projectservice.projects.domain.services;


import com.example.projectservice.projects.domain.model.aggregates.Project;
import com.example.projectservice.projects.domain.model.commands.CreateProjectCommand;
import com.example.projectservice.projects.domain.model.commands.DeleteProjectCommand;
import com.example.projectservice.projects.domain.model.commands.UpdateProjectProgressCommand;

import java.util.Optional;

public interface ProjectCommandService {
    Optional<Project> handle(CreateProjectCommand command);
    Optional<Project> handle(UpdateProjectProgressCommand command);
    void handle(DeleteProjectCommand command);
}
