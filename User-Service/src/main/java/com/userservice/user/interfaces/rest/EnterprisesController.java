package com.userservice.user.interfaces.rest;

import com.userservice.user.domain.services.EnterpriseCommandService;
import com.userservice.user.interfaces.rest.resources.CreateEnterpriseResource;
import com.userservice.user.interfaces.rest.resources.EnterpriseResource;
import com.userservice.user.interfaces.rest.resources.UpdateEnterpriseResource;
import com.userservice.user.interfaces.rest.transform.enterprise.CreateEnterpriseCommandFromResourceAssembler;
import com.userservice.user.interfaces.rest.transform.enterprise.EnterpriseResourceFromEntityAssembler;
import com.userservice.user.interfaces.rest.transform.enterprise.UpdateEnterpriseCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/enterprise", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Enterprise", description = "Available enterprise endpoints")
public class EnterprisesController {
    private final EnterpriseCommandService enterpriseCommandService;

    public EnterprisesController(EnterpriseCommandService enterpriseCommandService) {
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

    @PutMapping("/{enterpriseId}")
    @Operation(summary = "Update enterprise", description = "Update enterprise")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Enterprise updated"),
            @ApiResponse(responseCode = "404", description = "Enterprise not found")
    })
    public ResponseEntity<EnterpriseResource> updateEnterprise(@PathVariable UUID enterpriseId, @RequestBody UpdateEnterpriseResource updateEnterpriseResource) {
        var updateEnterpriseCommand = UpdateEnterpriseCommandFromResourceAssembler.toCommandFromResource(enterpriseId, updateEnterpriseResource);
        var enterprise = enterpriseCommandService.handle(updateEnterpriseCommand);
        if (enterprise.isEmpty()) return ResponseEntity.notFound().build();
        var updatedEnterpriseEntity = enterprise.get();
        var updatedEnterpriseResource = EnterpriseResourceFromEntityAssembler.toResourceFromEntity(updatedEnterpriseEntity);
        return ResponseEntity.ok(updatedEnterpriseResource);
    }
}
