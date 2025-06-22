package com.fromzero.chatservice.domain.model.aggregates;

import com.fromzero.chatservice.domain.model.commands.CreateChatCommand;
import com.fromzero.shared.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "chats")
@NoArgsConstructor
public class Chat extends AuditableAbstractAggregateRoot<Chat> {

    @Column (columnDefinition = "UUID", nullable = false, unique = true)
    private UUID projectId;

    @Column (columnDefinition = "UUID", nullable = false)
    private UUID user1Id;

    @Column (columnDefinition = "UUID", nullable = false)
    private UUID user2Id;

    @Column (nullable = false)
    private boolean closed = false;

    public Chat(CreateChatCommand command) {
        this.projectId = command.projectId();
        this.user1Id = command.user1();
        this.user2Id = command.user2();
        this.closed = false;
    }

    public UUID getProjectId() {
        return projectId;
    }

    public void setProjectId(UUID projectId) {
        this.projectId = projectId;
    }

    public UUID getUser1Id() {
        return user1Id;
    }

    public void setUser1Id(UUID user1Id) {
        this.user1Id = user1Id;
    }

    public UUID getUser2Id() {
        return user2Id;
    }

    public void setUser2Id(UUID user2Id) {
        this.user2Id = user2Id;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }
}
