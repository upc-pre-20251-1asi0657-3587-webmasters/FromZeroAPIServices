package com.fromzero.backend.comunication.application.internal.commandservices;

import com.fromzero.backend.comunication.domain.model.aggregates.SupportTicket;
import com.fromzero.backend.comunication.domain.model.commands.CreateSupportTicketCommand;
import com.fromzero.backend.comunication.domain.services.SupportTicketCommandService;
import com.fromzero.backend.comunication.infrastructure.persistence.jpa.repositories.SupportTicketsRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SupportTicketCommandServiceImpl implements SupportTicketCommandService {

    private final SupportTicketsRepository supportTicketsRepository;

    public SupportTicketCommandServiceImpl(SupportTicketsRepository supportTicketsRepository) {
        this.supportTicketsRepository = supportTicketsRepository;
    }

    @Override
    public Optional<SupportTicket> handle(CreateSupportTicketCommand command) {
        var supportTicket = new SupportTicket(command);
        this.supportTicketsRepository.save(supportTicket);
        return Optional.of(supportTicket);
    }
}
