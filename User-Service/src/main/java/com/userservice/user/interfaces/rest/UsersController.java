package com.userservice.user.interfaces.rest;

import com.userservice.user.domain.services.DeveloperCommandService;
import com.userservice.user.interfaces.rest.resources.CreateDeveloperResource;
import com.userservice.user.interfaces.rest.transform.CreateDeveloperCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/authentication", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Authentication", description = "Available Authentication Endpoints")
public class UsersController {
    private final DeveloperCommandService developerCommandService;

    public UsersController(DeveloperCommandService developerCommandService) {
        this.developerCommandService = developerCommandService;
    }

    @PostMapping("/new-developer")
    @Operation(summary = "Sign up a new user", description = "Sign up a new user with the provided username, password, and roles.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created successfully."),
            @ApiResponse(responseCode = "400", description = "Bad request.")
    })
    public void newDeveloper(@RequestBody CreateDeveloperResource createDeveloperResource) {
        var createDeveloperCommand = CreateDeveloperCommandFromResourceAssembler.toCommandFromResource(createDeveloperResource);
        var developer = developerCommandService.handle(createDeveloperCommand);
        if (developer.isEmpty()) throw new IllegalArgumentException("Developer cannot be created");
        var developerCreated = developer.get();
    }
}
