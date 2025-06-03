package com.example.projectservice.projects.domain.state;


import com.example.projectservice.projects.domain.model.aggregates.Project;

public class CompletedState implements ProjectState {
    @Override
    public void startRecruitmentProcess(Project project) {
        throw new IllegalStateException("Cannot start recruitment process while project is completed");
    }

    @Override
    public void startProject(Project project) {
        throw new IllegalStateException("Cannot start project while project is completed");
    }

    @Override
    public void completeProject(Project project) {
        throw new IllegalStateException("Cannot complete project while project is completed");
    }
}
