package com.fromzero.deliverableservice.deliverables.infrastructure.clients;

import com.fromzero.deliverableservice.deliverables.domain.model.commands.UpdateProjectProgressCommand;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "project-service")
public interface ProjectServiceClient {

    @PutMapping(value= "/api/v1/projects/update-progress", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    void updateProjectProgress(@RequestBody UpdateProjectProgressCommand command);
}
