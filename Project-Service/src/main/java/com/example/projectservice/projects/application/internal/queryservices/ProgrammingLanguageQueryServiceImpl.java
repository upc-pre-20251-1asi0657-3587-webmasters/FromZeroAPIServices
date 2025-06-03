package com.example.projectservice.projects.application.internal.queryservices;


import com.example.projectservice.projects.domain.model.aggregates.ProgrammingLanguage;
import com.example.projectservice.projects.domain.model.queries.GetProgrammingLanguageByIdQuery;
import com.example.projectservice.projects.domain.services.ProgrammingLanguagesQueryService;
import com.example.projectservice.projects.infrastructure.persistence.jpa.repositories.ProgrammingLanguagesRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProgrammingLanguageQueryServiceImpl implements ProgrammingLanguagesQueryService {
    private final ProgrammingLanguagesRepository programmingLanguagesRepository;

    public ProgrammingLanguageQueryServiceImpl(ProgrammingLanguagesRepository programmingLanguagesRepository) {
        this.programmingLanguagesRepository = programmingLanguagesRepository;
    }

    @Override
    public Optional<ProgrammingLanguage> handle(GetProgrammingLanguageByIdQuery query) {
        return this.programmingLanguagesRepository.findProgrammingLanguageById(query.id());
    }
}
