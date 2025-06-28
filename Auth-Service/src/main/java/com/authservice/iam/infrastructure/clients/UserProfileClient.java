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

    private final UserProfileFeignClient userProfileFeignClient;

    public UserProfileClient(UserProfileFeignClient userProfileFeignClient) {
        this.userProfileFeignClient = userProfileFeignClient;
    }

    @Override
    public void createDeveloperProfile(UUID uuid, String email, String firstName, String lastName) {
        var request = new CreateDeveloperRequest(uuid, email, firstName, lastName);
        userProfileFeignClient.createDeveloperProfile(request);
    }

    @Override
    public void createEnterpriseProfile(UUID uuid, String email, String enterpriseName) {
        var request = new CreateEnterpriseRequest(uuid, email, enterpriseName);
        userProfileFeignClient.createEnterpriseProfile(request);
    }
}
