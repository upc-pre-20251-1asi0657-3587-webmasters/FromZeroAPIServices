package com.fromzero.deliverableservice.deliverables.domain.model.aggregates;

import com.fromzero.deliverableservice.deliverables.domain.valueobjects.ProjectTypeEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class DefaultDeliverable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProjectTypeEnum projectTypeEnum;

    @Column(nullable = false)
    private String name;

    @Lob
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private int weeksToComplete;

    @Column(nullable = false)
    private int orderNumber;



    public DefaultDeliverable() {}

    public DefaultDeliverable(ProjectTypeEnum projectTypeEnum, String name, String description, int weeksToComplete, int orderNumber) {
        this.projectTypeEnum = projectTypeEnum;
        this.name = name;
        this.description = description;
        this.weeksToComplete = weeksToComplete;
        this.orderNumber = orderNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public ProjectTypeEnum getProjectTypeEnum() {
        return projectTypeEnum;
    }
    public void setProjectTypeEnum(ProjectTypeEnum projectTypeEnum) {
        this.projectTypeEnum = projectTypeEnum;
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
    public int getWeeksToComplete() {
        return weeksToComplete;
    }
    public void setWeeksToComplete(int weeksToComplete) {
        this.weeksToComplete = weeksToComplete;
    }
    public int getOrderNumber() {
        return orderNumber;
    }
    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }


}
