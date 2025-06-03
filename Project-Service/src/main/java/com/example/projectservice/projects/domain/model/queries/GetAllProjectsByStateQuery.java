package com.example.projectservice.projects.domain.model.queries;


import com.example.projectservice.projects.domain.valueobjects.ProjectStateEnum;

public record GetAllProjectsByStateQuery(ProjectStateEnum state) {
    public GetAllProjectsByStateQuery {
        if (state == null ) {
            throw new IllegalArgumentException("state cannot be null or empty");
        }
    }
}
