package com.userservice.user.interfaces.rest.transform.enterprise;

import com.userservice.user.domain.model.queries.GetEnterpriseByIdQuery;
import com.userservice.user.domain.model.valueobjects.enterprise.EnterpriseId;

import java.util.UUID;

public class GetEnterpriseByIdQueryFromPathVariable {
    public static GetEnterpriseByIdQuery toQueryFromPath(UUID enterpriseId) {
        return new GetEnterpriseByIdQuery(new EnterpriseId(enterpriseId));
    }
}
