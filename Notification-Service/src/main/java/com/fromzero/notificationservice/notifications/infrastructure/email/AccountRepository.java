package com.fromzero.notificationservice.notifications.infrastructure.email;

import com.fromzero.notificationservice.notifications.domain.model.aggregates.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {
    Optional<Account> findByUserId(UUID userId);
}