package com.userservice.user.domain.model.queries;

import com.userservice.user.domain.model.valueobjects.DeveloperId;

import java.util.UUID;

public record GetDeveloperByIdQuery(DeveloperId developerId) {
}
