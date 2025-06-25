package com.fromzero.chatservice.interfaces.rest.resources;

import java.util.UUID;

public record CreateChatResource(
        Long projectId,
        UUID user1,
        UUID user2,
        String projectName,
        String ownerImgUrl) {
}
