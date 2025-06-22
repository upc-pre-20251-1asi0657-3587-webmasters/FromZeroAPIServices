package com.fromzero.chatservice.application.internal.queryservices;

import com.fromzero.chatservice.domain.model.aggregates.ChatMessage;
import com.fromzero.chatservice.domain.model.queries.PaginatedChatMessageQuery;
import com.fromzero.chatservice.domain.model.queries.ValidateUserAndProject;
import com.fromzero.chatservice.domain.services.ChatMessageQueryService;
import com.fromzero.chatservice.infrastructure.persistence.repository.ChatMessageRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChatMessageQueryServiceImpl implements ChatMessageQueryService {
    private final ChatMessageRepository chatMessageRepository;

    public ChatMessageQueryServiceImpl(ChatMessageRepository chatMessageRepository) {
        this.chatMessageRepository = chatMessageRepository;
    }

    @Override
    public Optional<Page<ChatMessage>> handle(PaginatedChatMessageQuery paginatedChatMessageQuery) {
        Pageable pageable = PageRequest.of(paginatedChatMessageQuery.page(), paginatedChatMessageQuery.size());

        Page<ChatMessage> chatMessagesPage = chatMessageRepository.findByProjectIdOrderByTimestampDesc(paginatedChatMessageQuery.projectId(), pageable);

        return Optional.of(chatMessagesPage);
    }
}
