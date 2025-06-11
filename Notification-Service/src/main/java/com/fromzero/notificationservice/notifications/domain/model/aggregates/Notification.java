package com.fromzero.notificationservice.notifications.domain.model.aggregates;

import com.fromzero.notificationservice.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.fromzero.notificationservice.notifications.domain.model.commands.CreateNotificationCommand;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Setter
@Getter
public class Notification extends AuditableAbstractAggregateRoot<Notification> {
    @Column(nullable = false)
    private String Title;

    @Column(nullable= false)
    private UUID userID;

    public Notification(CreateNotificationCommand command) {
        this.Title = command.Title();
        this.userID = command.UserId();
    }

    public Notification() {}
}
