package com.userservice.user.interfaces.rest;

import com.userservice.user.domain.services.DeveloperCommandService;
import com.userservice.user.interfaces.rest.resources.CreateDeveloperResource;
import com.userservice.user.interfaces.rest.resources.DeveloperResource;
import com.userservice.user.interfaces.rest.resources.UpdateDeveloperResource;
import com.userservice.user.interfaces.rest.transform.developer.CreateDeveloperCommandFromResourceAssembler;
import com.userservice.user.interfaces.rest.transform.developer.DeveloperResourceFromEntityAssembler;
import com.userservice.user.interfaces.rest.transform.developer.UpdateDeveloperCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/developers", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Developers", description = "Available developers endpoints")
public class DevelopersController {
    private final DeveloperCommandService developerCommandService;

    public DevelopersController(DeveloperCommandService developerCommandService) {
        this.developerCommandService = developerCommandService;
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
