package com.example.projectservice.projects.domain.state;


import com.example.projectservice.projects.domain.model.aggregates.Project;
import com.example.projectservice.projects.domain.valueobjects.ProjectStateEnum;

public class LookingForDeveloperState implements ProjectState {

    @Override
    public void startRecruitmentProcess(Project project) {
        System.out.println("Already looking for developers");
    }

    @Override
    public void startProject(Project project) {
        if (project.getDeveloper() == null) {
            throw new IllegalStateException("Can't start projectId without a developer assigned");
        }

       System.out.println("Starting projectId " + project.getName());
        project.setState(ProjectStateEnum.IN_PROCESS);
    }

    @Override
    public void completeProject(Project project) {
        throw new IllegalStateException("Cannot complete projectId while looking for developers");
    }
}
