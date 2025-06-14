package com.fromzero.deliverableservice.deliverables.interfaces;


import com.fromzero.deliverableservice.deliverables.application.internal.commandservices.DefaultDeliverableCommandServiceImpl;
import com.fromzero.deliverableservice.deliverables.domain.model.commands.CreateDefaultDeliverableCommand;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/default-deliverables")
@Tag(name = "Default Deliverables", description = "Default Deliverables Management Endpoints")
public class DefaultDeliverableController {
    private final DefaultDeliverableCommandServiceImpl defaultDeliverableService;

    public DefaultDeliverableController(DefaultDeliverableCommandServiceImpl defaultDeliverableService) {
        this.defaultDeliverableService = defaultDeliverableService;
    }


    @PostMapping
    public ResponseEntity<Void> createDefaultDeliverables(@RequestBody CreateDefaultDeliverableCommand resource) {
        try {
            this.defaultDeliverableService.handle(resource);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            System.err.println("Error creating default deliverables: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
