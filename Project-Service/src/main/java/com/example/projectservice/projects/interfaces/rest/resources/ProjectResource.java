package com.example.projectservice.projects.interfaces.rest.resources;

//import com.acme.fromzeroapi.developer_branch_projects.domain.model.aggregates.Developer;


import com.example.projectservice.projects.domain.model.aggregates.Framework;
import com.example.projectservice.projects.domain.model.aggregates.ProgrammingLanguage;

import java.util.List;

public record ProjectResource(
        Long id, String name, String description, String state, Double progress,
        String ownerId, String developerId, List<String> candidatesList,
        List<ProgrammingLanguage> languages, List<Framework> frameworks, String type,
        String budget, String methodologies) {
    public ProjectResource{
        if(id==null || name==null || description==null){
            throw new NullPointerException("id is null");
        }
    }

    public ProjectResource(Long id,String name, String description, String state,
                           Double progress, String ownerId,List<String> candidatesList,
                           List<ProgrammingLanguage> languages, List<Framework> frameworks,
                           String type,String budget,String methodologies){
        this(id,name,description,state,progress,ownerId,null,candidatesList
                ,languages,frameworks, type,budget,methodologies);
    }

}
