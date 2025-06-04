package com.fromzero.deliverableservice.deliverables.interfaces.acl;


import com.fromzero.deliverableservice.deliverables.domain.services.DeliverableCommandService;
import org.springframework.stereotype.Service;

@Service
public class DeliverableContextFacade {

    private final DeliverableCommandService deliverableCommandService;

    public DeliverableContextFacade(DeliverableCommandService deliverableCommandService) {
        this.deliverableCommandService = deliverableCommandService;
    }
}