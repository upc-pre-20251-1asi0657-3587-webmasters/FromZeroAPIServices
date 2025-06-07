package com.userservice.user.interfaces.rest.transform.developer;

import com.userservice.user.domain.model.queries.GetDeveloperByIdQuery;
import com.userservice.user.domain.model.valueobjects.developer.DeveloperId;

import java.util.UUID;

public class GetDeveloperByIdQueryFromPathVariable {
    public static GetDeveloperByIdQuery toQueryFromPath(UUID developerId) {
        return new GetDeveloperByIdQuery(new DeveloperId(developerId));
    }
}
