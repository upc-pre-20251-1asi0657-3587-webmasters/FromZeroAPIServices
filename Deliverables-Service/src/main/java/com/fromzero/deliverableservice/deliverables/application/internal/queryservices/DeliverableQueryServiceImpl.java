package com.fromzero.deliverableservice.deliverables.application.internal.queryservices;


import com.fromzero.deliverableservice.deliverables.domain.model.aggregates.Deliverable;
import com.fromzero.deliverableservice.deliverables.domain.model.queries.GetAllDeliverablesByProjectIdQuery;
import com.fromzero.deliverableservice.deliverables.domain.model.queries.GetCompletedDeliverablesQuery;
import com.fromzero.deliverableservice.deliverables.domain.model.queries.GetDeliverableByIdQuery;
import com.fromzero.deliverableservice.deliverables.domain.services.DeliverableQueryService;
import com.fromzero.deliverableservice.deliverables.infrastructure.persistence.jpa.repositories.DeliverableRepository;
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
        return this.deliverableRepository.findAllByProjectId(Long.valueOf(query.projectId()));
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
