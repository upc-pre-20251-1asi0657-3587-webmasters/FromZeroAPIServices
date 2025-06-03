package com.userservice.user.infrastructure.persistence.jpa.repositories;

import com.userservice.user.domain.model.aggregates.Developer;
import com.userservice.user.domain.model.valueobjects.DeveloperEmail;
import com.userservice.user.domain.model.valueobjects.DeveloperId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, DeveloperId> {
    Optional<Developer> findByDeveloperId(DeveloperId developerId);
    Optional<Developer> findByDeveloperEmail(DeveloperEmail developerEmail);
    boolean existsByDeveloperEmail(DeveloperEmail developerEmail);
}
