package com.authservice.iam.infrastructure.clients;

import com.authservice.iam.application.ports.output.DeveloperProfileGateway;
import com.authservice.iam.interfaces.messaging.events.CreateDeveloperRequest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class UserProfileClient implements DeveloperProfileGateway {
    private final RestTemplate restTemplate;

    public UserProfileClient(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @Override
    public void createDeveloperProfile(UUID uuid, String email) {
        var request = new CreateDeveloperRequest(uuid, email);
        restTemplate.postForEntity("http://userservice:8082/api/v1/developers/new-developer", request, Void.class);
    }
}