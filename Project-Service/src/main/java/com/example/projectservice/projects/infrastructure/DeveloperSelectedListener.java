package com.example.projectservice.projects.infrastructure;

import com.example.projectservice.projects.domain.model.events.DeveloperSelectedEvent;
import com.example.projectservice.projects.domain.model.aggregates.Project;
import com.example.projectservice.projects.domain.valueobjects.ProjectStateEnum;
import com.example.projectservice.projects.infrastructure.persistence.jpa.repositories.ProjectRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.JmsListener;

@Configuration
public class DeveloperSelectedListener {

    private final ProjectRepository projectRepository;


    public DeveloperSelectedListener(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @JmsListener(destination = "developer.selected")
    public void onDeveloperSelected(DeveloperSelectedEvent event) {
        Project project = projectRepository.findById(event.getProjectId())
                .orElseThrow(() -> new IllegalArgumentException("Project not found with ID: " + event.getProjectId()));


        project.setDeveloper(event.getDeveloperId());
        project.setState(ProjectStateEnum.IN_PROCESS);

        projectRepository.save(project);

        System.out.println("Developer " + event.getDeveloperId() + " assigned to project " + event.getProjectId());
    }
}
