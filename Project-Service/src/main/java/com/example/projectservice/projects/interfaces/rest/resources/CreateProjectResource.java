package com.example.projectservice.projects.interfaces.rest.resources;



import com.example.projectservice.projects.domain.valueobjects.ProjectTypeEnum;

import java.util.List;

public record CreateProjectResource(
        String name,
        String description,
        String ownerId,
        List<Long> languages,
        List<Long> frameworks,
        ProjectTypeEnum type,
        String budget,
        String methodologies){
}
