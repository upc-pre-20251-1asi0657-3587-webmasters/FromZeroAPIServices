package com.fromzero.backend.comunication.domain.services;


import com.fromzero.backend.comunication.domain.model.aggregates.SupportTicket;
import com.fromzero.backend.comunication.domain.model.commands.CreateSupportTicketCommand;

import java.util.Optional;

public interface SupportTicketCommandService {
    Optional<SupportTicket> handle(CreateSupportTicketCommand command);
}
