package com.fromzero.notificationservice.notifications.domain.model.events;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeveloperSelectedEvent {
    private Long projectId;
    private String developerId;
    private String candidateId;

    public DeveloperSelectedEvent() {

    }

}
