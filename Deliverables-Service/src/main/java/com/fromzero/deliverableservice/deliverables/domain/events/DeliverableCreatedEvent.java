package com.fromzero.deliverableservice.deliverables.domain.events;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
// DeliverableCreatedEvent.java
public class DeliverableCreatedEvent {
    private String userEmail;
    private String deliverableName;
    private LocalDateTime deadline;

}

