package com.example.projectservice.deliverables.domain.services;



import com.example.projectservice.deliverables.domain.model.aggregates.Deliverable;
import com.example.projectservice.deliverables.domain.model.commands.*;

import java.util.List;
import java.util.Optional;

public interface DeliverableCommandService {
    Optional<Deliverable> handle(CreateDeliverableCommand command);
    void handle(List<CreateDeliverableCommand> commands);
    Optional<Deliverable> handle(UpdateDeveloperDescriptionCommand command);
    Optional<Deliverable> handle(UpdateDeliverableStatusCommand command);
    Optional<Deliverable> handle(UpdateDeliverableCommand command);
    void handle(DeleteDeliverableCommand command);
}
