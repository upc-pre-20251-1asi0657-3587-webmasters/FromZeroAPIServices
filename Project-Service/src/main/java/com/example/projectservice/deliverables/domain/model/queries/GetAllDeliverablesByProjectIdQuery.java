package com.example.projectservice.deliverables.domain.model.queries;


import com.example.projectservice.projects.domain.model.aggregates.Project;

public record GetAllDeliverablesByProjectIdQuery(Project project) {
}
