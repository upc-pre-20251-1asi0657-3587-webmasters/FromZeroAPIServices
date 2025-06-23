package com.fromzero.chatservice.domain.model.aggregates;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fromzero.chatservice.domain.model.commands.CreateChatMessageCommand;
import com.fromzero.chatservice.domain.model.valueobjects.MessageType;
import com.fromzero.shared.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "chat_message")
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage extends AuditableAbstractAggregateRoot<ChatMessage> {

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String sender;

    @Column(nullable = false)
    private Long projectId;

    @Column(columnDefinition = "UUID", nullable = false)
    @JsonProperty("senderId")
    private UUID senderId;

    @Column(nullable = false)
    private MessageType type;

    @Column(nullable = false)
    private Instant timestamp;

    public ChatMessage(UUID senderId, String sender, String s, String join) {
        this.senderId = senderId;
        this.sender = sender;
        this.content = s;
        this.type = MessageType.valueOf(join);
        this.timestamp = Instant.now();
    }

    public ChatMessage(CreateChatMessageCommand command) {
        this.senderId = command.senderId();
        this.sender = command.sender();
        this.content = command.content();
        this.projectId = command.projectId();
        this.type = command.type();
        this.timestamp = Instant.now();
    }

    public ChatMessage(String sender, MessageType type, String content) {
        this.sender = sender;
        this.type = type;
        this.content = content;
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
                "senderId=" + senderId +
                ", sender='" + sender + '\'' +
                ", content='" + content + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public UUID getSenderId() {
        return senderId;
    }

    public void setSenderId(UUID senderId) {
        this.senderId = senderId;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public static class Builder {
        private String sender;
        private MessageType type;
        private String content;
        private UUID projectId;
        private UUID senderId;
        private Instant timestamp;

        public Builder sender(String sender) {
            this.sender = sender;
            return this;
        }

        public Builder type(MessageType type) {
            this.type = type;
            return this;
        }

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public Builder projectId(UUID projectId) {
            this.projectId = projectId;
            return this;
        }

        public Builder senderId(UUID senderId) {
            this.senderId = senderId;
            return this;
        }

        public Builder timestamp(Instant timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public ChatMessage build() {
            return new ChatMessage(sender, type, content);
        }
    }

    // Método estático para usar el Builder
    public static Builder builder() {
        return new Builder();
    }
}
