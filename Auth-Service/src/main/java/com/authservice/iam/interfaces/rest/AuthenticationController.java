package com.authservice.iam.interfaces.rest;

import com.authservice.iam.domain.services.UserCommandService;
import com.authservice.iam.interfaces.rest.resources.AuthenticatedUserResource;
import com.authservice.iam.interfaces.rest.resources.SignInResource;
import com.authservice.iam.interfaces.rest.resources.SignUpDeveloperResource;
import com.authservice.iam.interfaces.rest.resources.DeveloperResource;
import com.authservice.iam.interfaces.rest.transform.AuthenticatedUserResourceFromEntityAssembler;
import com.authservice.iam.interfaces.rest.transform.SignInCommandFromResourceAssembler;
import com.authservice.iam.interfaces.rest.transform.SignUpDeveloperCommandFromResourceAssembler;
import com.authservice.iam.interfaces.rest.transform.DeveloperResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/authentication", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Authentication", description = "Available Authentication Endpoints")
public class AuthenticationController {
    private final UserCommandService userCommandService;

    public AuthenticationController(UserCommandService userCommandService) {
        this.userCommandService = userCommandService;
    }

    @PostMapping("/sign-up/developer")
    @Operation(summary = "Sign up a new developer", description = "Sign up a new developer with the provided data.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Developer created successfully."),
            @ApiResponse(responseCode = "400", description = "Bad request.")
    })
    public ResponseEntity<DeveloperResource> signUpDeveloper(@RequestBody SignUpDeveloperResource signUpDeveloperResource) {
        var signUpDeveloperCommand = SignUpDeveloperCommandFromResourceAssembler.toCommandFromResource(signUpDeveloperResource);
        var developer = userCommandService.handle(signUpDeveloperCommand);
        if (developer.isEmpty()) return ResponseEntity.badRequest().build();
        var developerEntity = developer.get();
        var developerResource = DeveloperResourceFromEntityAssembler.toResourceFromEntity(developerEntity);
        return new ResponseEntity<>(developerResource, HttpStatus.CREATED);
    }

    @PostMapping("/sign-in")
    @Operation(summary = "Sign in a user", description = "Sign in a user with the provided username and password.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User signed in successfully."),
            @ApiResponse(responseCode = "404", description = "User not found.")
    })
    public ResponseEntity<AuthenticatedUserResource> signIn(@RequestBody SignInResource resource) {
        var signInCommand = SignInCommandFromResourceAssembler.toCommandFromResource(resource);
        var authenticatedUserResult = userCommandService.handle(signInCommand);
        if (authenticatedUserResult.isEmpty()) return ResponseEntity.notFound().build();
        var authenticatedUser = authenticatedUserResult.get();
        var authenticatedUserResource = AuthenticatedUserResourceFromEntityAssembler.toResourceFromEntity(authenticatedUser.getLeft(), authenticatedUser.getRight());
        return ResponseEntity.ok(authenticatedUserResource);
    }
}
