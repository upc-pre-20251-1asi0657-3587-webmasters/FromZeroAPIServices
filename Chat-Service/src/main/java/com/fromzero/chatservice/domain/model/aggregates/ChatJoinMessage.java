package com.fromzero.chatservice.domain.model.aggregates;

import com.fromzero.chatservice.domain.model.valueobjects.MessageType;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

public class ChatJoinMessage {
    private String content;
    private String sender;
    private UUID senderId;
    private MessageType type;
    private Instant timestamp;

    public ChatJoinMessage(UUID senderId, String sender, String content) {
        this.senderId = senderId;
        this.content = content;
        this.sender = sender;
        this.type = MessageType.JOIN;
        this.timestamp = Instant.now();
    }

    @Override
    public String toString() {
        return "ChatJoinMessage{" +
                "content='" + content + '\'' +
                ", sender='" + sender + '\'' +
                ", senderId=" + senderId +
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
}
