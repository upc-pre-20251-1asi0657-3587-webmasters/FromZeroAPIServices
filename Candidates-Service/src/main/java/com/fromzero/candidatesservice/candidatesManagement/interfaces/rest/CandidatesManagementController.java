package com.fromzero.candidatesservice.candidatesManagement.interfaces.rest;

import com.fromzero.candidatesservice.candidatesManagement.domain.model.commands.ApplyToProjectCommand;
import com.fromzero.candidatesservice.candidatesManagement.domain.model.commands.SelectCandidateByDeveloperIdCommand;
import com.fromzero.candidatesservice.candidatesManagement.domain.model.commands.SelectCandidateCommand;
import com.fromzero.candidatesservice.candidatesManagement.domain.model.queries.GetAllCandidatesByProjectIdQuery;
import com.fromzero.candidatesservice.candidatesManagement.domain.services.CandidateCommandService;
import com.fromzero.candidatesservice.candidatesManagement.domain.services.CandidateQueryService;
import com.fromzero.candidatesservice.candidatesManagement.infrastructure.clients.DeveloperClient;
import com.fromzero.candidatesservice.candidatesManagement.interfaces.rest.resources.ApplyToProjectResource;
import com.fromzero.candidatesservice.candidatesManagement.interfaces.rest.resources.CandidateResource;
import com.fromzero.candidatesservice.candidatesManagement.interfaces.rest.transform.CandidateResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/candidates-management", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Candidates Management", description = "Endpoints for managing candidates for each project")
public class CandidatesManagementController {

    private final CandidateCommandService candidateCommandService;
    private final CandidateQueryService candidateQueryService;
    private final DeveloperClient developerClient;

    public CandidatesManagementController(CandidateCommandService candidateCommandService,
                                          CandidateQueryService candidateQueryService, DeveloperClient developerClient) {
        this.candidateCommandService = candidateCommandService;
        this.candidateQueryService = candidateQueryService;
        this.developerClient = developerClient;
    }

    @Operation(summary = "Get all candidates by projectId")
    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<CandidateResource>> getAllCandidatesByProjectId(@PathVariable Long projectId) {
        var query = new GetAllCandidatesByProjectIdQuery(projectId);
        var candidates = candidateQueryService.handle(query);

        var candidateResources = candidates.stream()
                .map(CandidateResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(candidateResources);
    }

    @Operation(summary = "Select a candidate for a project")
    @PatchMapping("/project/{projectId}/candidate/{developerId}/select")
    public ResponseEntity<CandidateResource> selectCandidate(@PathVariable Long projectId, @PathVariable UUID developerId) {
        var command = new SelectCandidateByDeveloperIdCommand(developerId, projectId);
        var candidate = candidateCommandService.handle(command);
        if (candidate.isEmpty()) return ResponseEntity.badRequest().build();
        var candidateResource = CandidateResourceFromEntityAssembler.toResourceFromEntity(candidate.get());
        return ResponseEntity.ok(candidateResource);
    }

    @Operation(summary = "Apply to a project")
    @PostMapping("/project/{projectId}/apply")
    public ResponseEntity<CandidateResource> applyToProject(@PathVariable Long projectId,
                                                            @RequestBody ApplyToProjectResource resource) {

        var dev = developerClient.getDeveloperById(resource.developerId().toString());

        var command = new ApplyToProjectCommand(
                dev.id(),
                projectId,
                dev.firstName(),
                dev.lastName(),
                dev.description()
        );

        var candidate = candidateCommandService.handle(command);
        if (candidate.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var candidateResource = CandidateResourceFromEntityAssembler.toResourceFromEntity(candidate.get());
        return ResponseEntity.status(HttpStatus.CREATED).body(candidateResource);
    }
}
