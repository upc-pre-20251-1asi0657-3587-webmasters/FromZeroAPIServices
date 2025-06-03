package com.fromzero.backend.comunication.interfaces.rest.transform;


import com.fromzero.backend.comunication.domain.model.aggregates.SupportTicket;
import com.fromzero.backend.comunication.interfaces.rest.resources.SupportTicketResource;

public class SupportTicketResourceFromEntityAssembler {

    public static SupportTicketResource toResourceFromEntity(SupportTicket entity) {
        return new SupportTicketResource(entity.getId(), entity.getTitle(), entity.getType(), entity.getDescription(), entity.getSender(), entity.getCreationDate());
    }
}
