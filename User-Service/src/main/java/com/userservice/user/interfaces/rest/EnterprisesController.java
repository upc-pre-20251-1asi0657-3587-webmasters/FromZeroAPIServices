package com.userservice.user.interfaces.rest;

import com.userservice.user.domain.services.EnterpriseCommandService;
import com.userservice.user.domain.services.EnterpriseQueryService;
import com.userservice.user.interfaces.rest.resources.CreateEnterpriseResource;
import com.userservice.user.interfaces.rest.resources.EnterpriseResource;
import com.userservice.user.interfaces.rest.resources.UpdateEnterpriseResource;
import com.userservice.user.interfaces.rest.transform.enterprise.CreateEnterpriseCommandFromResourceAssembler;
import com.userservice.user.interfaces.rest.transform.enterprise.EnterpriseResourceFromEntityAssembler;
import com.userservice.user.interfaces.rest.transform.enterprise.GetEnterpriseByIdQueryFromPathVariable;
import com.userservice.user.interfaces.rest.transform.enterprise.UpdateEnterpriseCommandFromResourceAssembler;
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
@RequestMapping(value = "/api/v1/enterprise", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Enterprise", description = "Available enterprise endpoints")
public class EnterprisesController {
    private final EnterpriseCommandService enterpriseCommandService;
    private final EnterpriseQueryService enterpriseQueryService;

    public EnterprisesController(EnterpriseCommandService enterpriseCommandService, EnterpriseQueryService enterpriseQueryService) {
        this.enterpriseCommandService = enterpriseCommandService;
        this.enterpriseQueryService = enterpriseQueryService;
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

    @GetMapping
    @Operation(summary = "Get all enterprises", description = "Get all the enterprises available in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Enterprises retrieved successfully."),
            @ApiResponse(responseCode = "401", description = "Unauthorized.")
    })
    public ResponseEntity<List<EnterpriseResource>> getAllEnterprises() {
        var enterprises = enterpriseQueryService.handle();
        var enterpriseResources = enterprises.stream().map(EnterpriseResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(enterpriseResources);
    }

    @GetMapping(value = "/{enterpriseId}")
    @Operation(summary = "Get enterprise by id", description = "Get the enterprise with the given id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Enterprise retrieved successfully."),
            @ApiResponse(responseCode = "404", description = "Enterprise not found."),
    })
    public ResponseEntity<EnterpriseResource> getEnterpriseById(@PathVariable UUID enterpriseId) {
        var getEnterpriseByIdQuery = GetEnterpriseByIdQueryFromPathVariable.toQueryFromPath(enterpriseId);
        var enterprise = enterpriseQueryService.handle(getEnterpriseByIdQuery);
        if (enterprise.isEmpty()) return ResponseEntity.notFound().build();
        var enterpriseEntity = enterprise.get();
        var enterpriseResource = EnterpriseResourceFromEntityAssembler.toResourceFromEntity(enterpriseEntity);
        return ResponseEntity.ok(enterpriseResource);
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
