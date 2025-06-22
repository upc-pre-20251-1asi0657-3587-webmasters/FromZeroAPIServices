package com.fromzero.candidatesservice.candidatesManagement.application.internal.commandservices;

import com.fromzero.candidatesservice.candidatesManagement.domain.model.commands.SelectCandidateByDeveloperIdCommand;
import com.fromzero.candidatesservice.candidatesManagement.domain.model.events.DeveloperAppliedEvent;
import com.fromzero.candidatesservice.candidatesManagement.domain.model.events.DeveloperSelectedEvent;
import com.fromzero.candidatesservice.candidatesManagement.domain.model.aggregates.Candidate;
import com.fromzero.candidatesservice.candidatesManagement.domain.model.commands.ApplyToProjectCommand;
import com.fromzero.candidatesservice.candidatesManagement.domain.model.commands.SelectCandidateCommand;
import com.fromzero.candidatesservice.candidatesManagement.domain.services.CandidateCommandService;
import com.fromzero.candidatesservice.candidatesManagement.infrastructure.eventPublisher.CandidatesPublisher;
import com.fromzero.candidatesservice.candidatesManagement.infrastructure.persistence.jpa.repositories.CandidateRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class CandidatesManagementCommandServiceImpl implements CandidateCommandService {

    private final CandidateRepository candidateRepository;
    private final CandidatesPublisher candidatesPublisher;

    public CandidatesManagementCommandServiceImpl(CandidateRepository candidateRepository, CandidatesPublisher candidatesPublisher) {
        this.candidateRepository = candidateRepository;
        this.candidatesPublisher = candidatesPublisher;
    }


    @Override
    public Optional<Candidate> handle(ApplyToProjectCommand command) {
        boolean alreadyApplied = candidateRepository
                .existsByProjectIdAndDeveloperId(command.projectId(), command.developerId());

        if (alreadyApplied) {
            throw new IllegalArgumentException("Candidate already applied to this project");
        }

        var candidate = new Candidate(
                command.developerId(),
                command.projectId(),
                command.firstName(),
                command.lastName(),
                command.description()
        );

        candidateRepository.save(candidate);

        DeveloperAppliedEvent event = new DeveloperAppliedEvent();
        event.setProjectId(command.projectId());
        event.setDeveloperId(String.valueOf(command.developerId()));
        candidatesPublisher.publishApplied(event);

        return Optional.of(candidate);
    }

    //TODO: Hay que pasar la actualización del estado de un proyecto cuando se selecciona un candidato
    @Override
    public Optional<Candidate> handle(SelectCandidateByDeveloperIdCommand command) {
        var candidate = candidateRepository.findByDeveloperIdAndProjectId(command.developerId(), command.projectId())
                .orElseThrow(() -> new IllegalArgumentException("Candidate not found"));

        if (!Objects.equals(candidate.getProjectId(), command.projectId())) {
            throw new IllegalArgumentException("Candidate does not belong to the specified project");
        }

        if (candidate.isSelected()) {
            throw new IllegalStateException("Candidate has already been selected");
        }

        candidate.setSelected(true);
        candidateRepository.save(candidate);

        DeveloperSelectedEvent event = new DeveloperSelectedEvent();
        event.setCandidateId(String.valueOf(candidate.getCandidateId()));
        event.setDeveloperId(String.valueOf(candidate.getDeveloperId()));
        event.setProjectId(candidate.getProjectId());
        candidatesPublisher.publishSelected(event);


        return Optional.of(candidate);
    }


}
