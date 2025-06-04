package com.userservice.user.domain.model.queries;

import com.userservice.user.domain.model.valueobjects.developer.DeveloperId;

public record GetDeveloperByIdQuery(DeveloperId developerId) {
}
