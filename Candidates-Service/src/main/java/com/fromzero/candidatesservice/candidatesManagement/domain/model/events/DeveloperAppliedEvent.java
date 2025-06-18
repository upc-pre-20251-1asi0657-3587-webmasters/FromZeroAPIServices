package com.fromzero.candidatesservice.candidatesManagement.domain.model.events;

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
