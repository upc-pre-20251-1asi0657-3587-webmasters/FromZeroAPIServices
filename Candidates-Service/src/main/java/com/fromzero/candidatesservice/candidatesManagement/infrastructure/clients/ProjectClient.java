package com.fromzero.candidatesservice.candidatesManagement.infrastructure.clients;

import com.fromzero.candidatesservice.candidatesManagement.interfaces.rest.resources.ProjectResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Project-Service")
public interface ProjectClient {
    @GetMapping("/api/v1/projects/{projectId}")
    ProjectResponse getProjectById(@PathVariable("projectId") Long projectId);
}
