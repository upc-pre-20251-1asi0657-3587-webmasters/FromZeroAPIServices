package com.example.projectservice.projects.domain.services;




import com.example.projectservice.projects.domain.model.entities.HighlightProject;
import com.example.projectservice.projects.domain.model.queries.GetAllHighlightProjectsQuery;

import java.util.List;

public interface HighlightProjectsQueryService {
    List<HighlightProject> handle(GetAllHighlightProjectsQuery query);
}
