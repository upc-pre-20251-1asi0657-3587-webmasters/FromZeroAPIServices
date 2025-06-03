package com.example.projectservice.deliverables.interfaces;


import com.example.projectservice.deliverables.domain.model.commands.DeleteDeliverableCommand;
import com.example.projectservice.deliverables.domain.model.commands.UpdateDeliverableCommand;
import com.example.projectservice.deliverables.domain.model.commands.UpdateDeliverableStatusCommand;
import com.example.projectservice.deliverables.domain.model.commands.UpdateDeveloperDescriptionCommand;
import com.example.projectservice.deliverables.domain.model.queries.GetAllDeliverablesByProjectIdQuery;
import com.example.projectservice.deliverables.domain.model.queries.GetCompletedDeliverablesQuery;
import com.example.projectservice.deliverables.domain.model.queries.GetDeliverableByIdQuery;
import com.example.projectservice.deliverables.domain.services.DeliverableCommandService;
import com.example.projectservice.deliverables.domain.services.DeliverableQueryService;
import com.example.projectservice.deliverables.interfaces.acl.ProfileContextFacade;
import com.example.projectservice.deliverables.interfaces.rest.resources.CreateDeliverableResource;
import com.example.projectservice.deliverables.interfaces.rest.resources.DeliverableResource;
import com.example.projectservice.deliverables.interfaces.rest.resources.UpdateDeliverableResource;
import com.example.projectservice.deliverables.interfaces.rest.transform.CreateDeliverableCommandFromResourceAssembler;
import com.example.projectservice.deliverables.interfaces.rest.transform.DeliverableResourceFromEntityAssembler;
import com.example.projectservice.projects.interfaces.acl.ProjectContextFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "api/v1/Projects/{projectId}/deliverables", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Deliverables", description = "Deliverables Management Endpoints")
public class DeliverableController {
    private final DeliverableCommandService deliverableCommandService;
    private final ProjectContextFacade projectContextFacade;
    private final DeliverableQueryService deliverableQueryService;
    private final ProfileContextFacade profileContextFacade;

    public DeliverableController(DeliverableCommandService deliverableCommandService,
                                 ProjectContextFacade projectContextFacade,
                                 DeliverableQueryService deliverableQueryService,
                                 ProfileContextFacade profileContextFacade) {
        this.deliverableCommandService = deliverableCommandService;
        this.projectContextFacade = projectContextFacade;
        this.deliverableQueryService = deliverableQueryService;
        this.profileContextFacade = profileContextFacade;
    }

    @Operation(summary = "Create deliverable")
    @PostMapping
    public ResponseEntity<DeliverableResource> createDeliverable(@RequestBody CreateDeliverableResource resource) {
        var project = this.projectContextFacade.getProjectById(resource.projectId());
        if (project == null) {
            return ResponseEntity.badRequest().build();
        }
        var createDeliverableCommand = CreateDeliverableCommandFromResourceAssembler.toCommandFromResource(resource);
        var deliverable = this.deliverableCommandService.handle(createDeliverableCommand);
        if (deliverable.isEmpty()) {
            return ResponseEntity.internalServerError().build();
        }

        var getAllDeliverablesByProjectId = new GetAllDeliverablesByProjectIdQuery(deliverable.get().getProject());
        var deliverables = this.deliverableQueryService.handle(getAllDeliverablesByProjectId);
        var getCompletedDeliverablesQuery = new GetCompletedDeliverablesQuery(deliverables);
        var completedDeliverables = deliverableQueryService.handle(getCompletedDeliverablesQuery);
        var totalDeliverables = deliverables.size();
        this.projectContextFacade.updateProjectProgress(deliverable.get().getProject().getId()
                , completedDeliverables, totalDeliverables);

        var deliverableResource = DeliverableResourceFromEntityAssembler.toResourceFromEntity(deliverable.get());
        return new  ResponseEntity<>(deliverableResource, HttpStatus.CREATED);
    }

    @GetMapping(value = "")
    @Operation(summary = "Get All Deliverables By Project Id")
    public ResponseEntity<List<DeliverableResource>> getAllDeliverablesByProjectId(@PathVariable Long projectId){
        var project = this.projectContextFacade.getProjectById(projectId);
        if (project==null)return ResponseEntity.badRequest().build();
        var getAllDeliverablesByProjectQuery = new GetAllDeliverablesByProjectIdQuery(project);
        var deliverables = this.deliverableQueryService.handle(getAllDeliverablesByProjectQuery);
        var deliverablesResources = deliverables.stream()
                .map(DeliverableResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(deliverablesResources);
    }

    @GetMapping(value = "/{deliverableId}")
    @Operation(summary = "Get Deliverable By Id")
    public ResponseEntity<DeliverableResource> getDeliverableById(@PathVariable Long deliverableId){
        var getDeliverableByIdQuery = new GetDeliverableByIdQuery(deliverableId);
        var deliverable = this.deliverableQueryService.handle(getDeliverableByIdQuery);
        if (deliverable.isEmpty())return ResponseEntity.badRequest().build();
        var deliverableResource = DeliverableResourceFromEntityAssembler.toResourceFromEntity(deliverable.get());
        return ResponseEntity.ok(deliverableResource);
    }

    @Operation(summary = "Upload Deliverable")
    @PatchMapping(value = "/{deliverableId}/upload")
    public ResponseEntity<DeliverableResource> sendDeliverable(@PathVariable Long deliverableId, @PathVariable Long projectId,
                                             @RequestBody String developerMessage){
        var updateDeveloperMessageCommand = new UpdateDeveloperDescriptionCommand(deliverableId,developerMessage, projectId);
        var deliverable = this.deliverableCommandService.handle(updateDeveloperMessageCommand);
        if (deliverable.isEmpty())return ResponseEntity.badRequest().build();
        var deliverableResource = DeliverableResourceFromEntityAssembler.toResourceFromEntity(deliverable.get());
        return ResponseEntity.ok(deliverableResource);
    }

    @Operation(summary = "Review Deliverable")
    @PatchMapping(value = "/{deliverableId}/review")
    public ResponseEntity<DeliverableResource> reviewDeliverable(@PathVariable Long deliverableId,
                                                                 @RequestBody Boolean accepted, @PathVariable String projectId){
        var updateDeliverableStatusCommand = new UpdateDeliverableStatusCommand(deliverableId,accepted);
        var deliverable = this.deliverableCommandService.handle(updateDeliverableStatusCommand);
        if (deliverable.isEmpty())return ResponseEntity.badRequest().build();

        if("APPROVED".equals(deliverable.get().getState())) {
            var getAllDeliverablesByProjectId = new GetAllDeliverablesByProjectIdQuery(deliverable.get().getProject());
            var deliverables = this.deliverableQueryService.handle(getAllDeliverablesByProjectId);
            var getCompletedDeliverablesQuery = new GetCompletedDeliverablesQuery(deliverables);
            var completedDeliverables = deliverableQueryService.handle(getCompletedDeliverablesQuery);
            var totalDeliverables = deliverables.size();
            var updatedProject = this.projectContextFacade.updateProjectProgress(deliverable.get().getProject().getId()
                    , completedDeliverables, totalDeliverables);
            if(updatedProject==null)return ResponseEntity.internalServerError().build();
            if(updatedProject.getProgress()==100.0){
                this.profileContextFacade.updateDeveloperCompletedProjects(updatedProject.getDeveloper());
            }
        }

        var deliverableResource = DeliverableResourceFromEntityAssembler.toResourceFromEntity(deliverable.get());
        return ResponseEntity.ok(deliverableResource);
    }

    @Operation(summary = "Edit Deliverable")
    @PutMapping(value = "/{deliverableId}")
    public ResponseEntity<DeliverableResource> updateDeliverable(@PathVariable Long deliverableId, @RequestBody UpdateDeliverableResource resource, @PathVariable String projectId) {
        UpdateDeliverableCommand command = new UpdateDeliverableCommand(deliverableId, resource.name(), resource.description(), resource.date());
        var updatedDeliverable = deliverableCommandService.handle(command);
        if (updatedDeliverable.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var deliverableResource = DeliverableResourceFromEntityAssembler.toResourceFromEntity(updatedDeliverable.get());
        return ResponseEntity.ok(deliverableResource);
    }

    @Operation(summary = "Delete Deliverable")
    @DeleteMapping(value = "/{deliverableId}/")
    public ResponseEntity<Void> deleteDeliverable(@PathVariable Long projectId, @PathVariable Long deliverableId) {
        var deleteDeliverableCommand = new DeleteDeliverableCommand(projectId,deliverableId);
        this.deliverableCommandService.handle(deleteDeliverableCommand);
        return ResponseEntity.noContent().build();
    }
}
