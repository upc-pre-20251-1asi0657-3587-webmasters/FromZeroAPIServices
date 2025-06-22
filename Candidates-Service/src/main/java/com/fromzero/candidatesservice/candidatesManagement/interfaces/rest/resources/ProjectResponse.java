package com.fromzero.candidatesservice.candidatesManagement.interfaces.rest.resources;

import java.util.List;

public record ProjectResponse(Long id, String name, String description, String state, Double progress,
                              String ownerId, String developerId, List<String> candidatesList, String type,
                              String budget, String methodologies) {
}
