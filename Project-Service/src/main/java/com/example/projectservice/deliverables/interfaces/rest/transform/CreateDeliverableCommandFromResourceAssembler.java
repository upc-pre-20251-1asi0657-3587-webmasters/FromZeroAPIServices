package com.example.projectservice.deliverables.interfaces.rest.transform;


import com.example.projectservice.deliverables.domain.model.commands.CreateDeliverableCommand;
import com.example.projectservice.deliverables.interfaces.rest.resources.CreateDeliverableResource;

public class CreateDeliverableCommandFromResourceAssembler {
    public static CreateDeliverableCommand toCommandFromResource(CreateDeliverableResource resource){
        return new CreateDeliverableCommand(resource.name(),resource.description(),resource.date(),resource.projectId(),1);
    }
}
