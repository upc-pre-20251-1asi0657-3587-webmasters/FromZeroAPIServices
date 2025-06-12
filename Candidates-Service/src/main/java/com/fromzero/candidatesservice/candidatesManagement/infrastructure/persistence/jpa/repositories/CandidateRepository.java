package com.fromzero.candidatesservice.candidatesManagement.infrastructure.persistence.jpa.repositories;


import com.fromzero.candidatesservice.candidatesManagement.domain.model.aggregates.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CandidateRepository extends JpaRepository<Candidate, UUID> {

    List<Candidate> findAllByProjectId(UUID projectId);

    boolean existsByProjectIdAndDeveloperId(UUID projectId, UUID developerId);

    Optional<Candidate> findCandidateByProjectIdAndDeveloperId(UUID projectId, UUID developerId);
}
