package com.fromzero.notificationservice.notifications.domain.model.events;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter

public class DeliverableCreatedEvent {
    private Long projectId;
    private String deliverableName;
    private LocalDateTime deadline;

}

