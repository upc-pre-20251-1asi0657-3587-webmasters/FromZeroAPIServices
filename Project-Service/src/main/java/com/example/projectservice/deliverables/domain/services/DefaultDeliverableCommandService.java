package com.example.projectservice.deliverables.domain.services;


import com.example.projectservice.deliverables.domain.model.commands.SeedDefaultDeliverablesCommand;

public interface DefaultDeliverableCommandService {
    void handle(SeedDefaultDeliverablesCommand command);

}
