package com.fromzero.deliverableservice.deliverables.domain.services;


import com.fromzero.deliverableservice.deliverables.domain.model.commands.CreateDefaultDeliverableCommand;
import com.fromzero.deliverableservice.deliverables.domain.model.commands.SeedDefaultDeliverablesCommand;

public interface DefaultDeliverableCommandService {
    void handle(SeedDefaultDeliverablesCommand command);
    void handle(CreateDefaultDeliverableCommand command);

}
