package com.fromzero.candidatesservice.candidatesManagement.domain.services;


import com.fromzero.candidatesservice.candidatesManagement.domain.model.aggregates.Candidate;
import com.fromzero.candidatesservice.candidatesManagement.domain.model.commands.ApplyToProjectCommand;
import com.fromzero.candidatesservice.candidatesManagement.domain.model.commands.SelectCandidateByDeveloperIdCommand;
import com.fromzero.candidatesservice.candidatesManagement.domain.model.commands.SelectCandidateCommand;

import java.util.Optional;

public interface CandidateCommandService {
    Optional<Candidate> handle(ApplyToProjectCommand command);
    Optional<Candidate> handle(SelectCandidateByDeveloperIdCommand command);
}
