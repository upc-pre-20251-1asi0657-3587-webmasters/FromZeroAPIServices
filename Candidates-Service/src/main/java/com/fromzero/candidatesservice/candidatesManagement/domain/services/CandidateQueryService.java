package com.fromzero.candidatesservice.candidatesManagement.domain.services;


import com.fromzero.candidatesservice.candidatesManagement.domain.model.aggregates.Candidate;
import com.fromzero.candidatesservice.candidatesManagement.domain.model.queries.GetAllCandidatesByProjectIdQuery;

import java.util.List;

public interface CandidateQueryService {
    List<Candidate> handle(GetAllCandidatesByProjectIdQuery query);
}
