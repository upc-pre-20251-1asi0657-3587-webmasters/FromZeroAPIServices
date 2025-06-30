package com.example.projectservice.projects.infrastructure.client;

import com.example.projectservice.projects.interfaces.rest.resources.CreateDefaultDeliverablesResource;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "deliverablesservice") // nombre registrado en Eureka
public interface DeliverablesClient {

    @PostMapping("/api/v1/default-deliverables")
    void createDefaultDeliverables(@RequestBody CreateDefaultDeliverablesResource request);
}
