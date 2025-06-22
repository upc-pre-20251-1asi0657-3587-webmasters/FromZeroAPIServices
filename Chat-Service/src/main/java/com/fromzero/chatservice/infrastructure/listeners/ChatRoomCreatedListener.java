package com.fromzero.chatservice.infrastructure.listeners;

import com.fromzero.chatservice.domain.model.aggregates.Chat;
import com.fromzero.chatservice.domain.model.commands.CreateChatCommand;
import com.fromzero.chatservice.domain.model.events.ChatRoomCreatedEvent;
import com.fromzero.chatservice.infrastructure.persistence.repository.ChatRepository;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class ChatRoomCreatedListener {
    private final ChatRepository chatRepository;

    public ChatRoomCreatedListener(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    @JmsListener(destination = "chat.created")
    public void listen(ChatRoomCreatedEvent event) {
        //this.chatRepository.save(new Chat(new CreateChatCommand(event.getProjectId(), event.getOwnerId(), event.getDeveloperId())));
        System.out.println("Chat room created for project ID: " + event.getProjectId() +
                ", Owner ID: " + event.getOwnerId() +
                ", Developer ID: " + event.getDeveloperId());
    }
}
