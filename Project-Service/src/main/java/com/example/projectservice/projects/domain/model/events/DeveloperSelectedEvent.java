package com.example.projectservice.projects.domain.model.events;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeveloperSelectedEvent {
    private Long projectId;
    private String developerId;

    public DeveloperSelectedEvent() {

    }

}
