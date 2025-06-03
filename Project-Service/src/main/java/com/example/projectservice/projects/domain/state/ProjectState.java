package com.example.projectservice.projects.domain.state;


import com.example.projectservice.projects.domain.model.aggregates.Project;

public interface ProjectState {

    void startRecruitmentProcess(Project project);
    void startProject(Project project);
    void completeProject(Project project);
}
