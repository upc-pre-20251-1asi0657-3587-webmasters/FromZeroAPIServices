package com.fromzero.candidatesservice.candidatesManagement.infrastructure.eventPublisher;

import com.fromzero.candidatesservice.candidatesManagement.domain.model.events.ChatRoomCreatedEvent;
import com.fromzero.candidatesservice.candidatesManagement.domain.model.events.DeveloperAppliedEvent;
import com.fromzero.candidatesservice.candidatesManagement.domain.model.events.DeveloperSelectedEvent;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class CandidatesPublisher {
    private final JmsTemplate jmsTemplate;

    public CandidatesPublisher(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void publishSelected(DeveloperSelectedEvent event) {
        jmsTemplate.convertAndSend("developer.selected", event);
    }

    public void publishApplied(DeveloperAppliedEvent event) {

        jmsTemplate.convertAndSend("developer.applied", event);
    }

    public void publishChatRoomCreatedEvent(ChatRoomCreatedEvent event) {
        jmsTemplate.convertAndSend("chat.created", event);
    }

}
