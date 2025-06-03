package com.example.projectservice.projects.interfaces.rest;



import com.example.projectservice.projects.domain.model.entities.HighlightProject;
import com.example.projectservice.projects.domain.model.queries.GetAllHighlightProjectsQuery;
import com.example.projectservice.projects.domain.services.HighlightProjectsQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/highlight-projects")
@Tag(name= "Highlight Projects", description = "Operations related to highlight projects")
public class HighlightProjectController {
    private final HighlightProjectsQueryService highlightProjectsQueryService;

    public HighlightProjectController(HighlightProjectsQueryService highlightProjectsQueryService) {
        this.highlightProjectsQueryService = highlightProjectsQueryService;
    }

    @Operation(summary = "Get all highlight projects")
    @GetMapping
    public ResponseEntity<List<HighlightProject>> getAllHighlightProjects(){
        return ResponseEntity.ok(highlightProjectsQueryService.handle(new GetAllHighlightProjectsQuery()));
    }
}
