package com.fromzero.backend.comunication.interfaces.rest;

import com.fromzero.backend.comunication.domain.model.aggregates.SupportTicket;
import com.fromzero.backend.comunication.domain.model.queries.GetAllSupportTicketQuery;
import com.fromzero.backend.comunication.domain.model.queries.GetSupportTicketByIdQuery;
import com.fromzero.backend.comunication.domain.services.SupportTicketCommandService;
import com.fromzero.backend.comunication.domain.services.SupportTicketQueryService;
import com.fromzero.backend.comunication.interfaces.rest.resources.CreateSupportTicketResource;
import com.fromzero.backend.comunication.interfaces.rest.resources.SupportTicketResource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class SupportTicketControllerTest {

    @Mock
    private SupportTicketCommandService supportTicketCommandService;

    @Mock
    private SupportTicketQueryService supportTicketQueryService;

    @InjectMocks
    private SupportTicketController supportTicketController;

    private SupportTicket supportTicket;
    private CreateSupportTicketResource createSupportTicketResource;
   private Long senderId;
    private String senderName;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        senderId = 2L;
        senderName = "Test User";

        supportTicket = new SupportTicket();
        supportTicket.setId(1L);
        supportTicket.setTitle("Test Ticket");
        supportTicket.setType("Test Type");
        supportTicket.setDescription("Test Description");
        supportTicket.setSender(senderId);
        supportTicket.setCreationDate(LocalDate.now());

        createSupportTicketResource = new CreateSupportTicketResource(
                supportTicket.getTitle(),
                supportTicket.getType(),
                supportTicket.getDescription(),
                senderId
        );
    }

//    @Test
//    void createSupportTicket() {
//        when(iamContextFacade.getUserById(createSupportTicketResource.senderId())).thenReturn(sender);
//        when(supportTicketCommandService.handle(any())).thenReturn(Optional.of(supportTicket));
//
//        assertNotNull(supportTicketController.createSupportTicket(createSupportTicketResource));
//    }

    @Test
    void getAllSupportTickets() {
        when(supportTicketQueryService.handle(any(GetAllSupportTicketQuery.class)))
                .thenReturn(List.of(supportTicket));

        assertNotNull(supportTicketController.getAllSupportTickets());
    }

    @Test
    void getSupportTicketById() {
        when(supportTicketQueryService.handle(any(GetSupportTicketByIdQuery.class)))
                .thenReturn(Optional.of(supportTicket));

        assertNotNull(supportTicketController.getSupportTicketById(supportTicket.getId()));
    }
}