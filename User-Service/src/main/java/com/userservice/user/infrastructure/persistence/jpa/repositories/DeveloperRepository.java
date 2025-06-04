package com.userservice.user.infrastructure.persistence.jpa.repositories;

import com.userservice.user.domain.model.aggregates.Developer;
import com.userservice.user.domain.model.valueobjects.developer.DeveloperEmail;
import com.userservice.user.domain.model.valueobjects.developer.DeveloperId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, DeveloperId> {
    Optional<Developer> findByDeveloperId(DeveloperId developerId);
    Optional<Developer> findByDeveloperEmail(DeveloperEmail developerEmail);
    boolean existsByDeveloperId(DeveloperId developerId);
    boolean existsByDeveloperEmail(DeveloperEmail developerEmail);
}
