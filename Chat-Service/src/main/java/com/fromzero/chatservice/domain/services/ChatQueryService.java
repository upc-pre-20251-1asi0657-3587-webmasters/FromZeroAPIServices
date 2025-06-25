package com.fromzero.chatservice.domain.services;

import com.fromzero.chatservice.domain.model.aggregates.Chat;
import com.fromzero.chatservice.domain.model.queries.GetChatByIdQuery;
import com.fromzero.chatservice.domain.model.queries.GetChatByProjectIdQuery;
import com.fromzero.chatservice.domain.model.queries.ValidateUserAndProject;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ChatQueryService {
    Optional<Chat> handle(GetChatByProjectIdQuery getChatByProjectIdQuery);
    Boolean handle(ValidateUserAndProject validateUserAndProject);
    Optional<List<Chat>> getChatsByUserId(UUID userId);
}
