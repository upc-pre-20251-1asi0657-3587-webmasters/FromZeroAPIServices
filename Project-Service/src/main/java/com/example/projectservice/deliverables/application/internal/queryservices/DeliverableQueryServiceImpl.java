package com.example.projectservice.deliverables.application.internal.queryservices;


import com.example.projectservice.deliverables.domain.model.aggregates.Deliverable;
import com.example.projectservice.deliverables.domain.model.queries.GetAllDeliverablesByProjectIdQuery;
import com.example.projectservice.deliverables.domain.model.queries.GetCompletedDeliverablesQuery;
import com.example.projectservice.deliverables.domain.model.queries.GetDeliverableByIdQuery;
import com.example.projectservice.deliverables.domain.services.DeliverableQueryService;
import com.example.projectservice.deliverables.infrastructure.persistence.jpa.repositories.DeliverableRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeliverableQueryServiceImpl implements DeliverableQueryService {
    private final DeliverableRepository deliverableRepository;

    public DeliverableQueryServiceImpl(DeliverableRepository deliverableRepository) {
        this.deliverableRepository = deliverableRepository;
    }

    @Override
    public List<Deliverable> handle(GetAllDeliverablesByProjectIdQuery query) {
        return this.deliverableRepository.findAllByProject(query.project());
    }

    @Override
    public Optional<Deliverable> handle(GetDeliverableByIdQuery query) {
        return this.deliverableRepository.findById(query.id());
    }

    @Override
    public Long handle(GetCompletedDeliverablesQuery query) {
        return query.deliverables().stream()
                .filter(deliverable->"APPROVED".equals(deliverable.getState())).count();
    }
}
