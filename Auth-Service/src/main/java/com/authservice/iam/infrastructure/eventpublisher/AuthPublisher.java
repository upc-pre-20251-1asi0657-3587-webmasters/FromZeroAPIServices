package com.authservice.iam.infrastructure.eventpublisher;

import com.authservice.iam.domain.events.AccountCreatedEvent;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;


    @Service
    public class AuthPublisher {

        private final JmsTemplate jmsTemplate;

        public AuthPublisher(JmsTemplate jmsTemplate) {
            this.jmsTemplate = jmsTemplate;
        }

        public void publish(AccountCreatedEvent event) {
            jmsTemplate.convertAndSend("account.created", event);
        }
    }