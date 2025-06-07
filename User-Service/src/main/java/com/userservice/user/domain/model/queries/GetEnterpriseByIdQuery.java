package com.userservice.user.domain.model.queries;

import com.userservice.user.domain.model.valueobjects.enterprise.EnterpriseId;

public record GetEnterpriseByIdQuery(EnterpriseId enterpriseId) {
}
