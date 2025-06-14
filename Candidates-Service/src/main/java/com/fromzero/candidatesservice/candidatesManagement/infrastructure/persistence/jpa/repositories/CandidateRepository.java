package com.fromzero.candidatesservice.candidatesManagement.infrastructure.persistence.jpa.repositories;


import com.fromzero.candidatesservice.candidatesManagement.domain.model.aggregates.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CandidateRepository extends JpaRepository<Candidate, UUID> {

    List<Candidate> findAllByProjectId(Long projectId);

    boolean existsByProjectIdAndDeveloperId(Long projectId, UUID developerId);

    Optional<Candidate> findCandidateByProjectIdAndDeveloperId(Long projectId, UUID developerId);
}
