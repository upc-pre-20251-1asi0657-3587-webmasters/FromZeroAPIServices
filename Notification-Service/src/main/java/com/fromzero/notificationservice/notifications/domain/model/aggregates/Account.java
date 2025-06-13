package com.fromzero.notificationservice.notifications.domain.model.aggregates;

import com.fromzero.notificationservice.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
public class Account  extends AuditableAbstractAggregateRoot<Account> {
    private String name;
    private String email;
    private String role;
    private UUID userId;

    public Account(UUID userId, String email, String name, String role) {
        this.userId = userId;
        this.email = email;
        this.name = name;
        this.role = role;
    }

    public Account() {

    }
}
