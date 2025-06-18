package com.fromzero.deliverableservice.deliverables.application.internal.commandservices;

import
        com.fromzero.deliverableservice.deliverables.domain.events.DeliverableCreatedEvent;
import com.fromzero.deliverableservice.deliverables.domain.exceptions.DeliverableAlreadyApprovedException;
import com.fromzero.deliverableservice.deliverables.domain.exceptions.DeliverableWithoutUploadException;
import com.fromzero.deliverableservice.deliverables.domain.exceptions.IllegalDeliverableDeadlineDateException;
import com.fromzero.deliverableservice.deliverables.domain.exceptions.IllegalDeliverableStateException;
import com.fromzero.deliverableservice.deliverables.domain.model.aggregates.Deliverable;
import com.fromzero.deliverableservice.deliverables.domain.model.commands.*;
import com.fromzero.deliverableservice.deliverables.domain.services.DeliverableCommandService;
import com.fromzero.deliverableservice.deliverables.domain.valueobjects.DeliverableStatus;
import com.fromzero.deliverableservice.deliverables.infrastructure.clients.ProjectServiceClient;
import com.fromzero.deliverableservice.deliverables.infrastructure.eventpublisher.DeliverablePublisher;
import com.fromzero.deliverableservice.deliverables.infrastructure.persistence.jpa.repositories.DefaultDeliverableRepository;
import com.fromzero.deliverableservice.deliverables.infrastructure.persistence.jpa.repositories.DeliverableRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DeliverableCommandServiceImpl implements DeliverableCommandService {
    private final DeliverableRepository deliverableRepository;

    private final DeliverablePublisher deliverablePublisher;
    private final ProjectServiceClient projectServiceClient;

    public DeliverableCommandServiceImpl(
            DeliverableRepository deliverableRepository, DeliverablePublisher deliverablePublisher, ProjectServiceClient projectServiceClient) {
        this.deliverableRepository = deliverableRepository;
        this.deliverablePublisher = deliverablePublisher;
        this.projectServiceClient = projectServiceClient;
    }

    @Override
    public Optional<Deliverable> handle(CreateDeliverableCommand command) {

        var deliverable = new Deliverable(command);

        //to assign the order number to a new deliverable
        Integer maxOrderValue=deliverableRepository.findMaxOrderNumberByProject(Long.valueOf(command.projectId()));
        int newOrderValue = 1;

        if(maxOrderValue==null){
            newOrderValue = 1;
        }else {
            newOrderValue=maxOrderValue+1;

            //to prevent the user from creating a deliverable with a deadline before the last one
            var lastDeliverable = this.deliverableRepository.findByProjectIdAndOrderNumber(Long.valueOf(command.projectId()), maxOrderValue);

            if(lastDeliverable.get().getDeadline().isAfter(deliverable.getDeadline())){
                throw new IllegalDeliverableDeadlineDateException("The deadline date is before the last deliverable");
            }
        }
        deliverable.setOrderNumber(newOrderValue);



        //to prevent the user from creating a deliverable with a deadline before the current date
        if(deliverable.getDeadline().isBefore(LocalDateTime.now())){
            throw new IllegalDeliverableDeadlineDateException("The deadline date is before the current date");
        }

        //to prevent the user from creating a deliverable without a title or description
        if(deliverable.getName()==null || deliverable.getName().isBlank() || deliverable.getDescription()==null || deliverable.getDescription().isBlank()){
            throw new IllegalArgumentException("The deliverable name and description are required");
        }

        this.deliverableRepository.save(deliverable);

        DeliverableCreatedEvent event = new DeliverableCreatedEvent();
        event.setProjectId(deliverable.getProjectId());
        event.setDeliverableName(deliverable.getName());
        event.setDeadline(deliverable.getDeadline());
        deliverablePublisher.publish(event);
        return Optional.of(deliverable);
    }

    @Override
    public void handle(List<CreateDeliverableCommand> commands) {
        //List<Deliverable> deliverablesList  = new ArrayList<>();
        commands.forEach(command -> {
            Optional<Deliverable> deliverable = this.handle(command);
            if(deliverable.isEmpty())throw new IllegalArgumentException();
            this.deliverableRepository.save(deliverable.get());
        });

    }

    @Override
    public Optional<Deliverable> handle(UpdateDeveloperDescriptionCommand command) {
        var deliverable = this.deliverableRepository.findById(command.deliverableId());


        if(deliverable.isEmpty()){
            throw new IllegalArgumentException();
        }


        //if the deliverable before this one is not approved
        if (deliverable.get().getOrderNumber()>1){
            var previousOrderNumber = deliverable.get().getOrderNumber() - 1;
            var previousDeliverable = deliverableRepository.findByProjectIdAndOrderNumber(
                    deliverable.get().getProjectId(), previousOrderNumber);

            if (previousDeliverable.isEmpty()) {
                throw new IllegalArgumentException("You can't upload this deliverable because the previous one doesn't exist");
            }

            if (previousDeliverable.get().getState() != DeliverableStatus.APPROVED) {
                throw new IllegalArgumentException("You can't upload this deliverable because the previous one is not approved");
            }
        }

        // if the deliverable was approved already
        if(deliverable.get().getState()==DeliverableStatus.APPROVED){
            throw new DeliverableAlreadyApprovedException("You can't upload a new deliverable. Deliverable already approved");
        }

        //if the upload is empty
        if(command.message()== null || command.message().isBlank()){
            throw new DeliverableWithoutUploadException("Deliverable does not have a developer message");
        }

        //if the deadline is before the current date
        if(deliverable.get().getDeadline().isBefore(LocalDateTime.now())){
            throw new IllegalArgumentException("The deadline is before the current date");
        }


        deliverable.get().setDeveloperDescription(command.message());
        deliverable.get().setState(DeliverableStatus.WAITING);
        this.deliverableRepository.save(deliverable.get());
        return deliverable;


    }

    @Override
    public Optional<Deliverable> handle(UpdateDeliverableStatusCommand command) {
        var deliverable = this.deliverableRepository.findById(command.deliverableId());
        if(deliverable.isEmpty()){
            throw new IllegalArgumentException();
        }

        var currentDeliverable = deliverable.get();

        //if there's no files/developerMessage from the developer
        if(currentDeliverable.getDeveloperDescription()==null){
            throw new DeliverableWithoutUploadException("There's no upload from the developer");
        }

        //if the deliverable was approved before
        if (currentDeliverable.getState() == DeliverableStatus.APPROVED){
            throw new DeliverableAlreadyApprovedException("Deliverable is already approved");
        }

        //if the status is not WAITING
        if (currentDeliverable.getState() != DeliverableStatus.WAITING) {
            throw new IllegalDeliverableStateException("The state must be WAITING");
        }

        if (command.accepted()) {
            currentDeliverable.setState(DeliverableStatus.APPROVED);
            this.deliverableRepository.save(currentDeliverable);

            Long projectId = currentDeliverable.getProjectId();
            long totalDeliverables = deliverableRepository.countByProjectId(projectId);
            long approvedDeliverables = deliverableRepository.countByProjectIdAndState(projectId, DeliverableStatus.APPROVED);
            System.out.println("total de deliverables " + totalDeliverables + ", deliverables aprobados: " + approvedDeliverables);


            double progress = ((double) approvedDeliverables / totalDeliverables) * 100.0;

            projectServiceClient.updateProjectProgress(new UpdateProjectProgressCommand(projectId, progress));


            System.out.println("progreso del proyecto: " + progress);
        } else {
            currentDeliverable.setState(DeliverableStatus.REJECTED);
            this.deliverableRepository.save(currentDeliverable);
        }


        return Optional.of(currentDeliverable);
    }
    @Override
    public Optional<Deliverable> handle(UpdateDeliverableCommand command) {
        Optional<Deliverable> deliverableOptional = deliverableRepository.findById(command.deliverableId());
        if (deliverableOptional.isPresent()) {
            Deliverable deliverable = deliverableOptional.get();
            deliverable.setName(command.name());
            deliverable.setDescription(command.description());
            deliverable.setDeadline(LocalDateTime.parse(command.date()));

            //to prevent the user from creating a deliverable without a title or description
            if(deliverable.getName()==null || deliverable.getName().isBlank() || deliverable.getDescription()==null || deliverable.getDescription().isBlank()){
                throw new IllegalArgumentException("The deliverable name and description are required");
            }

            deliverableRepository.save(deliverable);
            return Optional.of(deliverable);
        }
        return Optional.empty();
    }

    @Override
    public void handle(DeleteDeliverableCommand command) {
        var deliverable = this.deliverableRepository.findById(command.deliverableId());

        //to prevent the user from deleting a deliverable that doesn't exist
        if(deliverable.isEmpty()){
            throw new IllegalArgumentException("The deliverable with the id %s doesn't exist".formatted(command.deliverableId()));
        }

        //to prevent the user from deleting an approved deliverable
        if(deliverable.get().getState() == DeliverableStatus.APPROVED){
            throw new IllegalArgumentException("You can't delete an approved deliverable");
        }

        //to prevent the user from deleting a deliverable that has an upload
        if(deliverable.get().getDeveloperDescription()!=null){
            throw new IllegalArgumentException("You can't delete a deliverable with an upload");
        }

        deliverableRepository.delete(deliverable.get());

        //to update the order number of the deliverables after deleting one
        List<Deliverable> deliverables = deliverableRepository.findAllByProjectId(Long.valueOf(command.projectId()));

        if (deliverables.isEmpty()) {
            throw new IllegalArgumentException("No deliverables found for the project");
        }

        for (int i = 0; i < deliverables.size(); i++) {
            Deliverable actualDeliverable = deliverables.get(i);
            actualDeliverable.setOrderNumber(i + 1);
            deliverableRepository.save(actualDeliverable);
        }
    }
}
