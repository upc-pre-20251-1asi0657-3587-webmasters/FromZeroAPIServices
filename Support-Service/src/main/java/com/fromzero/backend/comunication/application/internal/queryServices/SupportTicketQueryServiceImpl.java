package com.fromzero.backend.comunication.application.internal.queryServices;

import com.fromzero.backend.comunication.domain.model.aggregates.SupportTicket;
import com.fromzero.backend.comunication.domain.model.queries.GetAllSupportTicketQuery;
import com.fromzero.backend.comunication.domain.model.queries.GetSupportTicketByIdQuery;
import com.fromzero.backend.comunication.domain.services.SupportTicketQueryService;
import com.fromzero.backend.comunication.infrastructure.persistence.jpa.repositories.SupportTicketsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupportTicketQueryServiceImpl implements SupportTicketQueryService {
    private final SupportTicketsRepository supportTicketsRepository;

    public SupportTicketQueryServiceImpl(SupportTicketsRepository supportTicketsRepository) {
        this.supportTicketsRepository = supportTicketsRepository;
    }

    @Override
    public Optional<SupportTicket> handle(GetSupportTicketByIdQuery query) {
        return this.supportTicketsRepository.findById(query.id());
    }

    @Override
    public List<SupportTicket> handle(GetAllSupportTicketQuery query) {
        return this.supportTicketsRepository.findAll();
    }
}
