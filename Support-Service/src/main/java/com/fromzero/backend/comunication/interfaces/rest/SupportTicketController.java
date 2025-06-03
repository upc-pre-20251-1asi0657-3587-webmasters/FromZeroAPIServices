package com.fromzero.backend.comunication.interfaces.rest;


import com.fromzero.backend.comunication.domain.model.queries.GetAllSupportTicketQuery;
import com.fromzero.backend.comunication.domain.model.queries.GetSupportTicketByIdQuery;
import com.fromzero.backend.comunication.domain.services.SupportTicketCommandService;
import com.fromzero.backend.comunication.domain.services.SupportTicketQueryService;
import com.fromzero.backend.comunication.interfaces.rest.resources.CreateSupportTicketResource;
import com.fromzero.backend.comunication.interfaces.rest.resources.SupportTicketResource;
import com.fromzero.backend.comunication.interfaces.rest.transform.CreateSupportTicketCommandFromResourceAsembler;
import com.fromzero.backend.comunication.interfaces.rest.transform.SupportTicketResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value ="/api/v2/support-tickets")
@Tag(name = "Support Tickets", description = "Support Tickets management endpoint")
public class SupportTicketController {

    private final SupportTicketCommandService supportTicketCommandService;
    private final SupportTicketQueryService supportTicketQueryService;

    public SupportTicketController(SupportTicketCommandService supportTicketCommandService, SupportTicketQueryService supportTicketQueryService) {
        this.supportTicketCommandService = supportTicketCommandService;
        this.supportTicketQueryService = supportTicketQueryService;
    }

    @Operation(summary = "Create Support Ticket")
    @PostMapping
    public ResponseEntity<SupportTicketResource>createSupportTicket(@RequestBody CreateSupportTicketResource resource, Long id){
        var recipientUser = id;
        if(recipientUser == null){
            return ResponseEntity.badRequest().build();
        }
        var createSupportTicketCommand = CreateSupportTicketCommandFromResourceAsembler.toCommandFromResource(resource, recipientUser);
        var supportTicket = this.supportTicketCommandService.handle(createSupportTicketCommand);
        if(supportTicket.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        var supportTicketResource = SupportTicketResourceFromEntityAssembler.toResourceFromEntity(supportTicket.get());
        return new ResponseEntity<>(supportTicketResource, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all support tickets")
    @GetMapping
    public ResponseEntity<List<SupportTicketResource>> getAllSupportTickets(){
        var getAllSupportTicketsQuery = new GetAllSupportTicketQuery();
        var supportTickets = this.supportTicketQueryService.handle(getAllSupportTicketsQuery);
        var supportTicketsResource = supportTickets.stream()
                .map(SupportTicketResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(supportTicketsResource);
    }

    @Operation(summary = "Get support ticket by id")
    @GetMapping(value= "/{id}")
    public ResponseEntity<SupportTicketResource> getSupportTicketById(@PathVariable Long id){
        var getSupportTicketByIdQuery = new GetSupportTicketByIdQuery(id);
        var supportTicket = this.supportTicketQueryService.handle(getSupportTicketByIdQuery);
        if(supportTicket.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        var supportTicketResource = SupportTicketResourceFromEntityAssembler.toResourceFromEntity(supportTicket.get());
        return ResponseEntity.ok(supportTicketResource);
    }

    @GetMapping(value="/status")
    public String getStatus() {
        return "Support Ticket Service is running";
    }
}


