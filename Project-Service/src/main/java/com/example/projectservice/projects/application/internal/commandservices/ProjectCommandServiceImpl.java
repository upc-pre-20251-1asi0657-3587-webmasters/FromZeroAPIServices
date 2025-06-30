package com.example.projectservice.projects.application.internal.commandservices;

import com.example.projectservice.projects.domain.model.aggregates.Project;
import com.example.projectservice.projects.domain.model.commands.CreateProjectCommand;
import com.example.projectservice.projects.domain.model.commands.DeleteProjectCommand;
import com.example.projectservice.projects.domain.model.commands.UpdateProjectProgressCommand;
import com.example.projectservice.projects.domain.services.ProjectCommandService;
import com.example.projectservice.projects.domain.valueobjects.ProjectStateEnum;
import com.example.projectservice.projects.infrastructure.client.DeliverablesClient;
import com.example.projectservice.projects.infrastructure.persistence.jpa.repositories.ProjectRepository;
import com.example.projectservice.projects.interfaces.rest.resources.CreateDefaultDeliverablesResource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.util.Optional;

@Service
public class ProjectCommandServiceImpl implements ProjectCommandService {

    private final ProjectRepository projectRepository;
    private final DeliverablesClient deliverablesClient;

    public ProjectCommandServiceImpl(ProjectRepository projectRepository, DeliverablesClient deliverablesClient) {
        this.projectRepository = projectRepository;
        this.deliverablesClient = deliverablesClient;
    }

    @Override
    public Optional<Project> handle(CreateProjectCommand command) {
        System.out.println("ejecutando create projectId command");
        var project = new Project(command);
        this.projectRepository.save(project);

        project.getLanguages().addAll(command.languages());
        project.getFrameworks().addAll(command.frameworks());

        try {
            CreateDefaultDeliverablesResource request =
                    new CreateDefaultDeliverablesResource(project.getId().toString(), project.getType().toString());

            deliverablesClient.createDefaultDeliverables(request);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            System.err.println("HTTP Error creating default deliverables: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
        } catch (Exception e) {
            System.err.println("Error al conectar con el servicio de deliverables " + e.getMessage());
        }

        return Optional.of(project);
    }

    @Override
    public Optional<Project> handle(UpdateProjectProgressCommand command) {
        var project = projectRepository.findById(command.projectId());
        if (project.isEmpty()) throw new IllegalArgumentException("Project not found");

        var actualProject = project.get();
        actualProject.setProgress(command.progress());

        if (command.progress() >= 100.0) {
            actualProject.setState(ProjectStateEnum.COMPLETED);
        }

        System.out.println("estado del proyecto: " + actualProject.getState());

        projectRepository.save(actualProject);
        return Optional.of(actualProject);
    }


    @Override
    @Transactional
    public void handle(DeleteProjectCommand command) {
        var project = projectRepository.findById(command.projectId())
                .orElseThrow(() -> new IllegalArgumentException("Project not found"));


        //if the projectId's state is COMPLETED or IN PROCESS
        if (project.getState() == ProjectStateEnum.COMPLETED || project.getState() == ProjectStateEnum.IN_PROCESS) {
            throw new IllegalArgumentException("Cannot delete a completed or an in process projectId");
        }

        //if the projectId have a developerId working on it
        if(project.getDeveloper() != null) {
            throw new IllegalArgumentException("Cannot delete a projectId with an assigned developerId");
        }


        // NOTE: Aquí también podemos agregar la lógica de comunicación con el servicio de Deliverables
        // para eliminar o actualizar los deliverables antes de eliminar el proyecto si es necesario.
        // deliverableService.deleteDeliverablesForProject(projectId);

        this.projectRepository.delete(project);
    }


}
