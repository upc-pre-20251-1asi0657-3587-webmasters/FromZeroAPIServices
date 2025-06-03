package com.example.projectservice.projects.interfaces.rest.transform;


import com.example.projectservice.projects.domain.model.aggregates.Project;
import com.example.projectservice.projects.domain.valueobjects.ProjectStateEnum;
import com.example.projectservice.projects.interfaces.rest.resources.ProjectResource;

public class ProjectResourceFromEntityAssembler {
    public static ProjectResource toResourceFromEntity(Project entity){
        //varios if con validaciones para hacer un return
        if(entity.getState()== ProjectStateEnum.LOOKING_FOR_DEVELOPERS){
            /*
             * Get Project Resource where State is "En busqueda"
             * Returns ProjectResource
             */
            return new ProjectResource(entity.getId(),entity.getName(),entity.getDescription(),
                    entity.getState().name(), entity.getProgress(),entity.getEnterprise(),entity.getCandidates(),
                    entity.getLanguages(),entity.getFrameworks(),entity.getType().name(),
                    entity.getBudget(),entity.getMethodologies());
        }

        if(entity.getState()== ProjectStateEnum.IN_PROCESS){
            return new ProjectResource(entity.getId(),entity.getName(),entity.getDescription(),
                    entity.getState().name(), entity.getProgress(),entity.getEnterprise(),
                    entity.getDeveloper(),entity.getCandidates(),
                    entity.getLanguages(),entity.getFrameworks(),entity.getType().name(),
                    entity.getBudget(),entity.getMethodologies());
        }

        if (entity.getState() == ProjectStateEnum.COMPLETED) {
            return new ProjectResource(entity.getId(), entity.getName(), entity.getDescription(),
                    entity.getState().name(), entity.getProgress(), entity.getEnterprise(),
                    entity.getDeveloper(), entity.getCandidates(),
                    entity.getLanguages(), entity.getFrameworks(), entity.getType().name(),
                    entity.getBudget(), entity.getMethodologies());
        }

        throw new IllegalArgumentException("The state of the project is not valid");
    }
}
