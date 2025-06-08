package com.fromzero.notificationservice.notifications.domain.model.aggregates;

import com.fromzero.notificationservice.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
public class notification extends AuditableAbstractAggregateRoot<notification> {
    @Column(nullable = false)
    private String threadId;

    @Column(nullable = false)
    private List<String> labelIds;

    @Column(nullable = false)
    private String snippet;

    @Column(nullable = false)
    private String historyId;

    @Column(nullable = false)
    private Date internalDate;
}
