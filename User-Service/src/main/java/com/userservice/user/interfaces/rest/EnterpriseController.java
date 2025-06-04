package com.userservice.user.interfaces.rest;

import com.userservice.user.domain.services.EnterpriseCommandService;
import com.userservice.user.interfaces.rest.resources.CreateEnterpriseResource;
import com.userservice.user.interfaces.rest.transform.CreateEnterpriseCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/enterprise", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Enterprise", description = "Available enterprise endpoints")
public class EnterpriseController {
    private final EnterpriseCommandService enterpriseCommandService;

    public EnterpriseController(EnterpriseCommandService enterpriseCommandService) {
        this.enterpriseCommandService = enterpriseCommandService;
    }

    @PostMapping("/new-enterprise")
    @Operation(summary = "Create a new enterprise", description = "Create a new enterprise from auth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Enterprise created successfully."),
            @ApiResponse(responseCode = "400", description = "Bad request.")
    })
    public void newEnterprise(@RequestBody CreateEnterpriseResource createEnterpriseResource) {
        var createEnterpriseCommand = CreateEnterpriseCommandFromResourceAssembler.toCommandFromResource(createEnterpriseResource);
        var enterprise = enterpriseCommandService.handle(createEnterpriseCommand);
        if (enterprise.isEmpty()) throw new IllegalArgumentException("Enterprise cannot be created");
    }
}
