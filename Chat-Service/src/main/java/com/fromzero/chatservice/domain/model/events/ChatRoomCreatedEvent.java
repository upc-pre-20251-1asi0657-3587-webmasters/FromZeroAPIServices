package com.fromzero.chatservice.domain.model.events;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

public class ChatRoomCreatedEvent {
    Long projectId;
    UUID ownerId;
    UUID developerId;
    String projectName;
    String ownerImgUrl;

    public ChatRoomCreatedEvent() {
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public UUID getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(UUID ownerId) {
        this.ownerId = ownerId;
    }

    public UUID getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(UUID developerId) {
        this.developerId = developerId;
    }

    public String getOwnerImgUrl() {
        return ownerImgUrl;
    }

    public void setOwnerImgUrl(String ownerImgUrl) {
        this.ownerImgUrl = ownerImgUrl;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
