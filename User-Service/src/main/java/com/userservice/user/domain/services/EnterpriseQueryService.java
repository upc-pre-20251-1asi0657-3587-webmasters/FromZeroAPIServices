package com.userservice.user.domain.services;

import com.userservice.user.domain.model.aggregates.Enterprise;
import com.userservice.user.domain.model.queries.GetEnterpriseByIdQuery;

import java.util.List;
import java.util.Optional;

public interface EnterpriseQueryService {
    List<Enterprise> handle();
    Optional<Enterprise> handle(GetEnterpriseByIdQuery getEnterpriseByIdQuery);
}
