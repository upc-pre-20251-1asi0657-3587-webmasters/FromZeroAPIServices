package com.authservice.iam.infrastructure.clients;

import com.authservice.iam.interfaces.messaging.events.CreateDeveloperRequest;
import com.authservice.iam.interfaces.messaging.events.CreateEnterpriseRequest;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "User-Service")
public interface UserProfileFeignClient {
    @PostMapping("/api/v1/developers/new-developer")
    void createDeveloperProfile(@RequestBody CreateDeveloperRequest request);

    @PostMapping("/api/v1/enterprise/new-enterprise")
    void createEnterpriseProfile(@RequestBody CreateEnterpriseRequest request);
}
