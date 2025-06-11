package com.userservice.user.infrastructure.eventpublisher;

import com.userservice.user.domain.model.events.UserCreatedEvent;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;


    @Service
    public class UserPublisher {

        private final JmsTemplate jmsTemplate;

        public UserPublisher(JmsTemplate jmsTemplate) {
            this.jmsTemplate = jmsTemplate;
        }

        public void publishUserCreatedevent(UserCreatedEvent event) {
            jmsTemplate.convertAndSend("account.created", event);
        }
    }