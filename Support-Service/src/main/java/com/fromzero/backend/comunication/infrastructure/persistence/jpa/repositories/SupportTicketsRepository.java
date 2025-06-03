package com.fromzero.backend.comunication.infrastructure.persistence.jpa.repositories;

import com.fromzero.backend.comunication.domain.model.aggregates.SupportTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupportTicketsRepository extends JpaRepository<SupportTicket, Long> {

}
