package com.authservice.iam.infrastructure.persistence.jpa.repositories;

import com.authservice.iam.domain.model.aggregates.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsByUserEmail(String userEmail);
    Optional<User> findByUserEmail(String userEmail);
}
