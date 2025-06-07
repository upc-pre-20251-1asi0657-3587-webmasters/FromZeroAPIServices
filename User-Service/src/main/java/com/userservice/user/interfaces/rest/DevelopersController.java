package com.userservice.user.interfaces.rest;

import com.userservice.user.domain.model.queries.GetDeveloperByIdQuery;
import com.userservice.user.domain.services.DeveloperCommandService;
import com.userservice.user.domain.services.DeveloperQueryService;
import com.userservice.user.interfaces.rest.resources.CreateDeveloperResource;
import com.userservice.user.interfaces.rest.resources.DeveloperResource;
import com.userservice.user.interfaces.rest.resources.UpdateDeveloperResource;
import com.userservice.user.interfaces.rest.transform.developer.CreateDeveloperCommandFromResourceAssembler;
import com.userservice.user.interfaces.rest.transform.developer.DeveloperResourceFromEntityAssembler;
import com.userservice.user.interfaces.rest.transform.developer.GetDeveloperByIdQueryFromPathVariable;
import com.userservice.user.interfaces.rest.transform.developer.UpdateDeveloperCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/developers", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Developers", description = "Available developers endpoints")
public class DevelopersController {
    private final DeveloperCommandService developerCommandService;
    private final DeveloperQueryService developerQueryService;

    public DevelopersController(DeveloperCommandService developerCommandService, DeveloperQueryService developerQueryService) {
        this.developerCommandService = developerCommandService;
        this.developerQueryService = developerQueryService;
    }

    @PostMapping("/new-developer")
    @Operation(summary = "Create a new developer", description = "Create a new developer from auth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Developer created successfully."),
            @ApiResponse(responseCode = "400", description = "Bad request.")
    })
    public void newDeveloper(@RequestBody CreateDeveloperResource createDeveloperResource) {
        var createDeveloperCommand = CreateDeveloperCommandFromResourceAssembler.toCommandFromResource(createDeveloperResource);
        var developer = developerCommandService.handle(createDeveloperCommand);
        if (developer.isEmpty()) throw new IllegalArgumentException("Developer cannot be created");
    }

    @GetMapping
    @Operation(summary = "Get all developers", description = "Get all the developers available in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Developers retrieved successfully."),
            @ApiResponse(responseCode = "401", description = "Unauthorized.")
    })
    public ResponseEntity<List<DeveloperResource>> getAllDevelopers() {
        var developers = developerQueryService.handle();
        var developerResources = developers.stream().map(DeveloperResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(developerResources);
    }

    @GetMapping(value = "/{developerId}")
    @Operation(summary = "Get developer by id", description = "Get the developer with the given id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Developer retrieved successfully."),
            @ApiResponse(responseCode = "404", description = "Developer not found."),
    })
    public ResponseEntity<DeveloperResource> getDeveloperById(@PathVariable UUID developerId) {
        var getDeveloperByIdQuery = GetDeveloperByIdQueryFromPathVariable.toQueryFromPath(developerId);
        var developer = developerQueryService.handle(getDeveloperByIdQuery);
        if (developer.isEmpty()) return ResponseEntity.notFound().build();
        var developerEntity = developer.get();
        var developerResource = DeveloperResourceFromEntityAssembler.toResourceFromEntity(developerEntity);
        return ResponseEntity.ok(developerResource);
    }

    @PutMapping("/{developerId}")
    @Operation(summary = "Update developer", description = "Update developer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Developer updated"),
            @ApiResponse(responseCode = "404", description = "Developer not found")
    })
    public ResponseEntity<DeveloperResource> updateDeveloper(@PathVariable UUID developerId, @RequestBody UpdateDeveloperResource updateDeveloperResource) {
        var updateDeveloperCommand = UpdateDeveloperCommandFromResourceAssembler.toCommandFromResource(developerId, updateDeveloperResource);
        var developer = developerCommandService.handle(updateDeveloperCommand);
        if (developer.isEmpty()) return ResponseEntity.notFound().build();
        var updatedDeveloperEntity = developer.get();
        var updatedDeveloperResource = DeveloperResourceFromEntityAssembler.toResourceFromEntity(updatedDeveloperEntity);
        return ResponseEntity.ok(updatedDeveloperResource);
    }
}
