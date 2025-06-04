package com.example.projectservice.projects.application.internal.commandservices;

import com.example.projectservice.projects.domain.model.aggregates.Project;
import com.example.projectservice.projects.domain.model.commands.CreateProjectCommand;
import com.example.projectservice.projects.domain.model.commands.DeleteProjectCommand;
import com.example.projectservice.projects.domain.model.commands.UpdateProjectProgressCommand;
import com.example.projectservice.projects.domain.services.ProjectCommandService;
import com.example.projectservice.projects.domain.valueobjects.ProjectStateEnum;
import com.example.projectservice.projects.domain.valueobjects.ProjectTypeEnum;
import com.example.projectservice.projects.infrastructure.persistence.jpa.repositories.ProjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectCommandServiceImpl implements ProjectCommandService {

    private final ProjectRepository projectRepository;

    public ProjectCommandServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Optional<Project> handle(CreateProjectCommand command) {

        var project = new Project(command);
        this.projectRepository.save(project);

        project.getLanguages().addAll(command.languages());
        project.getFrameworks().addAll(command.frameworks());

        // NOTE: Aquí se haría la integración con el servicio de Deliverables.
        // Por ejemplo, podríamos hacer una llamada REST al servicio de Deliverables
        // para crear los deliverables asociados al proyecto.
        // Al realizar esta llamada, deberíamos manejar la respuesta y la posible excepción si el servicio de Deliverables falla.

        // List<Deliverable> deliverables = deliverableService.createDeliverablesForProject(project);
        // deliverableRepository.saveAll(deliverables);

        this.projectRepository.save(project);
        return Optional.of(project);
    }

    // NOTE: Esta logica deberia pasarse como una peticion al servicio de deliverables
    // para que este se encargue de crear los deliverables y almacenarnos, se puede enviar el UUID del projecto
    // y su tipo.
    // private List<Deliverable> addDefaultDeliverables(ProjectTypeEnum projectTypeEnum, Project project) {
    //     List<DefaultDeliverable> defaults = defaultDeliverableRepository.findByProjectTypeEnum(projectTypeEnum);
    //     LocalDate today = LocalDate.now();
    //     DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
    //     return defaults.stream().map(d -> {
    //         // to create de deadline date
    //         String deadline = today.plusWeeks(d.getWeeksToComplete()).format(formatter) + "T23:59:59";
    //         CreateDeliverableCommand deliverableCommand  = new CreateDeliverableCommand(
    //                 d.getName(),
    //                 d.getDescription(),
    //                 deadline,
    //                 project.getId(),
    //                 d.getOrderNumber()
    //         );
    //         Deliverable deliverable = new Deliverable(deliverableCommand, project);
    //         deliverableRepository.save(deliverable);
    //         return deliverable;
    //     }).toList();
    // }

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
