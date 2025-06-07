package com.userservice.user.application.internal.queryservices;

import com.userservice.user.domain.model.aggregates.Enterprise;
import com.userservice.user.domain.model.queries.GetEnterpriseByIdQuery;
import com.userservice.user.domain.services.EnterpriseQueryService;
import com.userservice.user.infrastructure.persistence.jpa.repositories.EnterpriseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnterpriseQueryServiceImpl implements EnterpriseQueryService {
    private final EnterpriseRepository enterpriseRepository;

    public EnterpriseQueryServiceImpl(EnterpriseRepository enterpriseRepository) {
        this.enterpriseRepository = enterpriseRepository;
    }

    @Override
    public List<Enterprise> handle() {
        return enterpriseRepository.findAll();
    }

    @Override
    public Optional<Enterprise> handle(GetEnterpriseByIdQuery getEnterpriseByIdQuery) {
        return enterpriseRepository.findByEnterpriseId(getEnterpriseByIdQuery.enterpriseId());
    }
}
