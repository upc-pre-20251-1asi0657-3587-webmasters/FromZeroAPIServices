package com.fromzero.candidatesservice.candidatesManagement.infrastructure.clients;

import com.fromzero.candidatesservice.candidatesManagement.interfaces.rest.resources.DeveloperProfile;
import com.fromzero.candidatesservice.candidatesManagement.interfaces.rest.resources.EnterpriseProfile;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "User-Service")
public interface UserClient {

    @GetMapping("/api/v1/developers/{developerId}")
    DeveloperProfile getDeveloperById(@PathVariable("developerId") String developerId);

    @GetMapping("/api/v1/enterprise/{enterpriseId}")
    EnterpriseProfile getEnterpriseById(@PathVariable("enterpriseId") String enterpriseId);
}