package com.example.projectservice.deliverables.domain.model.queries;




import com.example.projectservice.deliverables.domain.model.aggregates.Deliverable;

import java.util.List;

public record GetCompletedDeliverablesQuery(List<Deliverable> deliverables) {
}
