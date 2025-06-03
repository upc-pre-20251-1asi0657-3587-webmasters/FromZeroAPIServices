package com.fromzero.backend.comunication.domain.services;



import com.fromzero.backend.comunication.domain.model.aggregates.SupportTicket;
import com.fromzero.backend.comunication.domain.model.queries.GetAllSupportTicketQuery;
import com.fromzero.backend.comunication.domain.model.queries.GetSupportTicketByIdQuery;

import java.util.List;
import java.util.Optional;

public interface SupportTicketQueryService {
    Optional<SupportTicket> handle(GetSupportTicketByIdQuery query);
    List<SupportTicket> handle(GetAllSupportTicketQuery query);
}
