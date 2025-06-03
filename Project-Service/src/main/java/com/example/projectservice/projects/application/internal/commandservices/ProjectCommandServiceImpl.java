package com.example.projectservice.projects.application.internal.commandservices;


import com.example.projectservice.deliverables.domain.model.aggregates.DefaultDeliverable;
import com.example.projectservice.deliverables.domain.model.aggregates.Deliverable;
import com.example.projectservice.deliverables.domain.model.commands.CreateDeliverableCommand;
import com.example.projectservice.deliverables.domain.valueobjects.DeliverableStatus;
import com.example.projectservice.deliverables.infrastructure.persistence.jpa.repositories.DefaultDeliverableRepository;
import com.example.projectservice.deliverables.infrastructure.persistence.jpa.repositories.DeliverableRepository;
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
    private final DeliverableRepository deliverableRepository;
    private final DefaultDeliverableRepository defaultDeliverableRepository;

    public ProjectCommandServiceImpl(ProjectRepository projectRepository,
                                     DeliverableRepository deliverableRepository,
                                     DefaultDeliverableRepository defaultDeliverableRepository) {
        this.projectRepository = projectRepository;
        this.deliverableRepository = deliverableRepository;
        this.defaultDeliverableRepository = defaultDeliverableRepository;
    }

    @Override
    public Optional<Project> handle(CreateProjectCommand command) {

        var project = new Project(command);
        this.projectRepository.save(project);

        project.getLanguages().addAll(command.languages());
        project.getFrameworks().addAll(command.frameworks());

        List<Deliverable> deliverables = addDefaultDeliverables(project.getType(), project);
        deliverableRepository.saveAll(deliverables);

        this.projectRepository.save(project);
        return Optional.of(project);
    }


    private List<Deliverable> addDefaultDeliverables(ProjectTypeEnum projectTypeEnum, Project project) {
        List<DefaultDeliverable> defaults = defaultDeliverableRepository.findByProjectTypeEnum(projectTypeEnum);

        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

        return defaults.stream().map(d -> {
            // to create de deadline date
            String deadline = today.plusWeeks(d.getWeeksToComplete()).format(formatter) + "T23:59:59";

            CreateDeliverableCommand deliverableCommand  = new CreateDeliverableCommand(
                    d.getName(),
                    d.getDescription(),
                    deadline,
                    project.getId(),
                    d.getOrderNumber()
            );

            Deliverable deliverable = new Deliverable(deliverableCommand, project);
            deliverableRepository.save(deliverable);
            return deliverable;

        }).toList();
    }



    @Override
    public Optional<Project> handle(UpdateProjectProgressCommand command) {
        var project = command.project();
        project.setProgress(command.progress());

        boolean allDeliverablesApproved = deliverableRepository.findAllByProject(project)
                .stream()
                .allMatch(deliverable -> {
                    return deliverable.getState() == DeliverableStatus.APPROVED;
                });

        if (allDeliverablesApproved) {
            project.getStateHandler().completeProject(project);
            //project.setState(ProjectStateEnum.COMPLETED);
        }

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


        deliverableRepository.deleteAllByProjectId(project.getId());

        this.projectRepository.delete(project);
    }


}
