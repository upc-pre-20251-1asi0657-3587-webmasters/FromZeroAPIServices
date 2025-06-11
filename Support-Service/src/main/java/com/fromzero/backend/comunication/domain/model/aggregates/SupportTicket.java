package com.fromzero.backend.comunication.domain.model.aggregates;

import com.fromzero.backend.comunication.domain.model.commands.CreateSupportTicketCommand;
import com.fromzero.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Primary;

import java.time.LocalDate;

@Entity
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

    public String getTitle() {
        return title;
    }

    public String getType() {
        return Type;
    }

    public String getDescription() {
        return description;
    }

    public Long getSender() {
        return sender;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setType(String type) {
        Type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSender(Long sender) {
        this.sender = sender;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
}
