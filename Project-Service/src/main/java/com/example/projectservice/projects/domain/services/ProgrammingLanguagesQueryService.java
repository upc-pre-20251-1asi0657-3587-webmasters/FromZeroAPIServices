package com.example.projectservice.projects.domain.services;



import com.example.projectservice.projects.domain.model.aggregates.ProgrammingLanguage;
import com.example.projectservice.projects.domain.model.queries.GetProgrammingLanguageByIdQuery;

import java.util.Optional;

public interface ProgrammingLanguagesQueryService {
    Optional<ProgrammingLanguage> handle(GetProgrammingLanguageByIdQuery query);
}
