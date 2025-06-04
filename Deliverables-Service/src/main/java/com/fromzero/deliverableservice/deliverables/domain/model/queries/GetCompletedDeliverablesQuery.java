package com.fromzero.deliverableservice.deliverables.domain.model.queries;




import com.fromzero.deliverableservice.deliverables.domain.model.aggregates.Deliverable;

import java.util.List;

public record GetCompletedDeliverablesQuery(List<Deliverable> deliverables) {
}
