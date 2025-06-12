package com.fromzero.candidatesservice.candidatesManagement.application.internal.commandservices;

import com.fromzero.candidatesservice.candidatesManagement.domain.model.aggregates.Candidate;
import com.fromzero.candidatesservice.candidatesManagement.domain.model.commands.ApplyToProjectCommand;
import com.fromzero.candidatesservice.candidatesManagement.domain.model.commands.SelectCandidateCommand;
import com.fromzero.candidatesservice.candidatesManagement.domain.services.CandidateCommandService;
import com.fromzero.candidatesservice.candidatesManagement.infrastructure.persistence.jpa.repositories.CandidateRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class CandidatesManagementCommandServiceImpl implements CandidateCommandService {

    private final CandidateRepository candidateRepository;

    public CandidatesManagementCommandServiceImpl(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
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
        return Optional.of(candidate);
    }

    //TODO: Hay que pasar la actualización del estado de un proyecto cuando se selecciona un candidato
    @Override
    public Optional<Candidate> handle(SelectCandidateCommand command) {
        var candidate = candidateRepository.findById(command.candidateId())
                .orElseThrow(() -> new IllegalArgumentException("Candidate not found"));

        if (!Objects.equals(candidate.getProjectId(), command.projectId())) {
            throw new IllegalArgumentException("Candidate does not belong to the specified project");
        }

        if (candidate.isSelected()) {
            throw new IllegalStateException("Candidate has already been selected");
        }

        candidate.setSelected(true);
        candidateRepository.save(candidate);

        return Optional.of(candidate);
    }
}
