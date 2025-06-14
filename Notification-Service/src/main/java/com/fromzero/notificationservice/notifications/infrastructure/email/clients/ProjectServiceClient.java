package com.fromzero.notificationservice.notifications.infrastructure.email.clients;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

@Component
public class ProjectServiceClient {
    private final RestTemplate restTemplate;
    private final String projectServiceUrl;

    public ProjectServiceClient(RestTemplate restTemplate, @Value("http://project-service/api/v1/") String projectServiceUrl) {
        this.restTemplate = restTemplate;
        this.projectServiceUrl = projectServiceUrl;
    }

    public ProjectDto getProjectById(Long projectId) {
        return restTemplate.getForObject(projectServiceUrl + "/projects/" + projectId, ProjectDto.class);
    }
}
