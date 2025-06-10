package com.fromzero.deliverableservice.deliverables.domain.events;

import lombok.Getter;
import lombok.Setter;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@Getter
@Setter
// DeliverableCreatedEvent.java
public class DeliverableCreatedEvent {
    private String userEmail;
    private String deliverableName;
    private LocalDateTime deadline;

}

