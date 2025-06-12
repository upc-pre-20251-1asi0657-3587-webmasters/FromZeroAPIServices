package com.example.projectservice.projects.domain.model.commands;




import com.example.projectservice.projects.domain.model.aggregates.Framework;
import com.example.projectservice.projects.domain.model.aggregates.ProgrammingLanguage;
import com.example.projectservice.projects.domain.valueobjects.ProjectTypeEnum;

import java.util.List;

public record CreateProjectCommand(
        String name, String description, String enterprise,
        List<ProgrammingLanguage> languages, List<Framework> frameworks, ProjectTypeEnum type,
        String budget, String methodologies) {

}