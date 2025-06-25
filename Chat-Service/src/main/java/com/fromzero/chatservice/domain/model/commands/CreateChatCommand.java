package com.fromzero.chatservice.domain.model.commands;

import java.util.UUID;

public record CreateChatCommand(Long projectId, UUID user1, UUID user2, String projectName, String ownerImgUrl) {
}
