package com.fromzero.chatservice.domain.model.queries;

import java.util.UUID;

public record PaginatedChatMessageQuery(UUID projectId, int page, int size) {
}
