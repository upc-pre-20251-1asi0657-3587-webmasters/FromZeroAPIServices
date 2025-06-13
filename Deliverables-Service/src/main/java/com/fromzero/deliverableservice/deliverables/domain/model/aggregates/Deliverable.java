package com.fromzero.deliverableservice.deliverables.domain.model.aggregates;

import com.fromzero.deliverableservice.deliverables.domain.model.commands.CreateDeliverableCommand;
import com.fromzero.deliverableservice.deliverables.domain.valueobjects.DeliverableStatus;
import com.fromzero.deliverableservice.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Deliverable extends AuditableAbstractAggregateRoot<Deliverable> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Lob
    @Column(nullable = false,columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private LocalDateTime deadline;

    @Column(nullable = false)
    private DeliverableStatus state;

    @Column
    private String fileUrl;

    @Column(nullable = false)
    private String projectId;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String developerDescription;

    @Column(nullable = false)
    private int orderNumber;

    public Deliverable(CreateDeliverableCommand command) {
        this.name = command.name();
        this.description = command.description();
        this.deadline = LocalDateTime.parse(command.date());
        this.state = DeliverableStatus.PENDING;
        this.developerDescription = null;
        this.fileUrl = null;
        this.projectId = command.projectId();
        this.orderNumber = command.orderNumber();
    }

    public Deliverable(String name, String description, String deadline, String projectId, int orderNumber, DeliverableStatus state) {
        this.name = name;
        this.description = description;
        this.deadline = LocalDateTime.parse(deadline);
        this.projectId = projectId;
        this.orderNumber = orderNumber;
        this.state = DeliverableStatus.PENDING;
    }


    public Deliverable() {}


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public DeliverableStatus getState() {
        return state;
    }

    public void setState(DeliverableStatus state) {
        this.state = state;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getDeveloperDescription() {
        return developerDescription;
    }

    public void setDeveloperDescription(String developerDescription) {
        this.developerDescription = developerDescription;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }
}
