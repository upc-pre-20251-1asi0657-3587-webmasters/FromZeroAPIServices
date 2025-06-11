package com.fromzero.deliverableservice.deliverables.infrastructure.eventpublisher;

import com.fromzero.deliverableservice.deliverables.domain.events.DeliverableCreatedEvent;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;


    @Service
    public class DeliverablePublisher {

        private final JmsTemplate jmsTemplate;

        public DeliverablePublisher(JmsTemplate jmsTemplate) {
            this.jmsTemplate = jmsTemplate;
        }

        public void publish(DeliverableCreatedEvent event) {
            jmsTemplate.convertAndSend("deliverable.created", event);
        }
    }