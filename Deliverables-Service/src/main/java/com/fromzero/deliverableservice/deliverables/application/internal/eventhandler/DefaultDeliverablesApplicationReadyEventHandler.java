package com.fromzero.deliverableservice.deliverables.application.internal.eventhandler;


import com.fromzero.deliverableservice.deliverables.domain.model.commands.SeedDefaultDeliverablesCommand;
import com.fromzero.deliverableservice.deliverables.domain.services.DefaultDeliverableCommandService;
import com.fromzero.deliverableservice.deliverables.domain.valueobjects.ProjectTypeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class DefaultDeliverablesApplicationReadyEventHandler {

    private final DefaultDeliverableCommandService defaultDeliverableCommandService;
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultDeliverablesApplicationReadyEventHandler.class);


    public DefaultDeliverablesApplicationReadyEventHandler(DefaultDeliverableCommandService defaultDeliverableCommandService) {
        this.defaultDeliverableCommandService = defaultDeliverableCommandService;
    }

    @EventListener
    public void on(ApplicationReadyEvent event) {
        var appName = event.getApplicationContext().getApplicationName();
        LOGGER.info("----------- Seeding default deliverables for Application {} -----------", appName);

        for (ProjectTypeEnum type : ProjectTypeEnum.values()) {
            defaultDeliverableCommandService.handle(new SeedDefaultDeliverablesCommand(type));
        }

        LOGGER.info("----------- default deliverables seeding completed -----------");
    }

}
