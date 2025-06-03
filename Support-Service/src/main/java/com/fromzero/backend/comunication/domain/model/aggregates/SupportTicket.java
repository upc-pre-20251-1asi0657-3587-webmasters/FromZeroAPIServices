package com.fromzero.backend.comunication.domain.model.aggregates;

import com.fromzero.backend.comunication.domain.model.commands.CreateSupportTicketCommand;
import com.fromzero.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class SupportTicket extends AuditableAbstractAggregateRoot<SupportTicket> {

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String Type;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Long sender;

    @Column(nullable = false)
    private LocalDate creationDate;

    public SupportTicket(CreateSupportTicketCommand createSupportTicketCommand) {
        this.title = createSupportTicketCommand.title();
        this.Type = createSupportTicketCommand.type();
        this.description = createSupportTicketCommand.description();
        this.sender = createSupportTicketCommand.sender();
        this.creationDate = LocalDate.now();
    }
    public SupportTicket() {}
}
