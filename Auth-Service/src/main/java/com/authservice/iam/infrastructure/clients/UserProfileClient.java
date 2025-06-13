package com.authservice.iam.infrastructure.clients;

import com.authservice.iam.application.ports.output.UserProfileGateway;
import com.authservice.iam.interfaces.messaging.events.CreateDeveloperRequest;
import com.authservice.iam.interfaces.messaging.events.CreateEnterpriseRequest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class UserProfileClient implements UserProfileGateway {
    private final RestTemplate restTemplate;

    public UserProfileClient(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @Override
    public void createDeveloperProfile(UUID uuid, String email, String firstName, String lastName) {
        var request = new CreateDeveloperRequest(uuid, email, firstName, lastName);
        restTemplate.postForEntity("http://localhost:9082/api/v1/developers/new-developer", request, Void.class);
    }

    @Override
    public void createEnterpriseProfile(UUID uuid, String email) {
        var request = new CreateEnterpriseRequest(uuid, email);
        restTemplate.postForEntity("http://localhost:9082/api/v1/enterprise/new-enterprise", request, Void.class);
    }
}