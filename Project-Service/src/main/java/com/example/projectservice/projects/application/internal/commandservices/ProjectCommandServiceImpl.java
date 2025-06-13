package com.example.projectservice.projects.application.internal.commandservices;

import com.example.projectservice.projects.domain.model.aggregates.Project;
import com.example.projectservice.projects.domain.model.commands.CreateProjectCommand;
import com.example.projectservice.projects.domain.model.commands.DeleteProjectCommand;
import com.example.projectservice.projects.domain.model.commands.UpdateProjectProgressCommand;
import com.example.projectservice.projects.domain.services.ProjectCommandService;
import com.example.projectservice.projects.domain.valueobjects.ProjectStateEnum;
import com.example.projectservice.projects.infrastructure.persistence.jpa.repositories.ProjectRepository;
import com.example.projectservice.projects.interfaces.rest.resources.CreateDefaultDeliverablesResource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class ProjectCommandServiceImpl implements ProjectCommandService {

    private final ProjectRepository projectRepository;
    private final RestTemplate restTemplate;

    public ProjectCommandServiceImpl(ProjectRepository projectRepository, RestTemplate restTemplate) {
        this.projectRepository = projectRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public Optional<Project> handle(CreateProjectCommand command) {
        System.out.println("ejecutando create project command");
        var project = new Project(command);
        this.projectRepository.save(project);

        project.getLanguages().addAll(command.languages());
        project.getFrameworks().addAll(command.frameworks());


        try {
            System.out.println("TIPO DE PROYECTO: "+project.getType());
            System.out.println("ID DEL PROYECTO: "+project.getId());

            String url = "http://deliverables-service/api/v1/default-deliverables";
            CreateDefaultDeliverablesResource request = new CreateDefaultDeliverablesResource(project.getId().toString(), project.getType().toString());
            restTemplate.postForEntity(url, request, Void.class);

            System.out.println("Deliverables por defecto creados con éxito.");
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            System.err.println("HTTP Error creating default deliverables: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
        } catch (Exception e) {
            System.err.println("Error al conectar con el servicio de deliverables " + e.getMessage());
        }

//        try {
//            String deliverablesServiceUrl = "http://deliverables-service/api/v1/Projects/" + project.getId() + "/deliverables";
//            restTemplate.postForEntity(deliverablesServiceUrl, deliverableRequest, Void.class);
//            System.out.println("Solicitud enviada al servicio de Deliverables para crear el deliverable.");
//        } catch (HttpClientErrorException | HttpServerErrorException e) {
//            System.err.println("HTTP Error creating deliverable: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
//        } catch (Exception e) {
//            System.err.println("Error al crear el deliverable: " + e.getMessage());
//        }

        return Optional.of(project);
    }

    @Override
    public Optional<Project> handle(UpdateProjectProgressCommand command) {
        var project = command.project();
        project.setProgress(command.progress());

        // NOTE: Dependiendo de la lógica de deliverables que se tenga, podríamos agregar una validación
        // para verificar si todos los deliverables han sido aprobados, lo que cambiaría el estado del proyecto.
        // boolean allDeliverablesApproved = deliverableRepository.findAllByProject(project)
        //         .stream()
        //         .allMatch(deliverable -> {
        //             return deliverable.getState() == DeliverableStatus.APPROVED;
        //         });
        //
        // if (allDeliverablesApproved) {
        //     project.getStateHandler().completeProject(project);
        //     //project.setState(ProjectStateEnum.COMPLETED);
        // }

        this.projectRepository.save(project);
        return Optional.of(project);
    }

    @Override
    @Transactional
    public void handle(DeleteProjectCommand command) {
        var project = projectRepository.findById(command.projectId())
                .orElseThrow(() -> new IllegalArgumentException("Project not found"));


        //if the project's state is COMPLETED or IN PROCESS
        if (project.getState() == ProjectStateEnum.COMPLETED || project.getState() == ProjectStateEnum.IN_PROCESS) {
            throw new IllegalArgumentException("Cannot delete a completed or an in process project");
        }

        //if the project have a developer working on it
        if(project.getDeveloper() != null) {
            throw new IllegalArgumentException("Cannot delete a project with an assigned developer");
        }


        // NOTE: Aquí también podemos agregar la lógica de comunicación con el servicio de Deliverables
        // para eliminar o actualizar los deliverables antes de eliminar el proyecto si es necesario.
        // deliverableService.deleteDeliverablesForProject(project);

        this.projectRepository.delete(project);
    }


}
