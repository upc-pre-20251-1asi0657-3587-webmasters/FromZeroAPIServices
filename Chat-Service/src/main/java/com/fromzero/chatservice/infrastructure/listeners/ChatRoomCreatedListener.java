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

    @JmsListener(destination = "chat.created", containerFactory = "topicListenerFactory")
    public void listen(ChatRoomCreatedEvent event) {
        try {
            System.out.println("Evento recibido: " + event);
            var chatCommand = new CreateChatCommand(event.getProjectId(), event.getOwnerId(), event.getDeveloperId());

            var chat =  new Chat(chatCommand);

            this.chatRepository.save(chat);
        } catch (Exception e) {
            System.err.println("Error al recibir el evento: " + e.getMessage());
            return;
        }
    }
}
