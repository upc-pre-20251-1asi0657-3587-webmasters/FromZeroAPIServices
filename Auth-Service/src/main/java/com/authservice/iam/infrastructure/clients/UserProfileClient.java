package com.authservice.iam.infrastructure.clients;

import com.authservice.iam.application.ports.output.UserProfileGateway;
import com.authservice.iam.interfaces.messaging.events.CreateDeveloperRequest;
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
    public void createUserProfile(UUID uuid, String email) {
        var request = new CreateDeveloperRequest(uuid, email);
        restTemplate.postForEntity("http://localhost:8081/api/v1/authentication/new-developer", request, Void.class);
    }
}