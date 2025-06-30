package com.fromzero.notificationservice.notifications.infrastructure.email.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "project-service")
public interface ProjectServiceClient {
    @GetMapping("/api/v1/projects/{id}")
    ProjectDto getProjectById(@PathVariable("id") Long id);
}

