package com.example.projectservice.projects.infrastructure;

import com.example.projectservice.projects.domain.model.events.DeveloperAppliedEvent;
import com.example.projectservice.projects.infrastructure.persistence.jpa.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DeveloperAppliedListener {

    private final ProjectRepository projectRepository;

    public DeveloperAppliedListener(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @JmsListener(destination = "developer.applied")
    public void onCandidateApplied(DeveloperAppliedEvent event) {
        Long projectId = event.getProjectId();
        UUID candidateId = UUID.fromString(event.getDeveloperId());

        projectRepository.findById(projectId).ifPresent(project -> {
            project.getCandidates().add(candidateId.toString());
            projectRepository.save(project);
            System.out.println("Candidate applied: " + candidateId + " to the project " + projectId);
        });
    }
}
