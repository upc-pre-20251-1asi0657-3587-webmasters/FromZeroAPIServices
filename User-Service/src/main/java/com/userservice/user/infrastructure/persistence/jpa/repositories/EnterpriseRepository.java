package com.userservice.user.infrastructure.persistence.jpa.repositories;

import com.userservice.user.domain.model.aggregates.Enterprise;
import com.userservice.user.domain.model.valueobjects.enterprise.EnterpriseEmail;
import com.userservice.user.domain.model.valueobjects.enterprise.EnterpriseId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnterpriseRepository extends JpaRepository<Enterprise, EnterpriseId> {
    Optional<Enterprise> findByEnterpriseId(EnterpriseId enterpriseId);
    boolean existsByEnterpriseEmail(EnterpriseEmail enterpriseEmail);
}
