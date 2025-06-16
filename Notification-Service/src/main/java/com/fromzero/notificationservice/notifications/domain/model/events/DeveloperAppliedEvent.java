package com.fromzero.notificationservice.notifications.domain.model.events;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeveloperAppliedEvent {
    private Long projectId;
    private String developerId;

    public DeveloperAppliedEvent() {

    }
}
