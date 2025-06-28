package com.fromzero.candidatesservice.candidatesManagement.domain.model.aggregates;



import com.fromzero.candidatesservice.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
public class Candidate extends AuditableAbstractAggregateRoot<Candidate> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID candidateId;

    @Column(nullable = false)
    private UUID developerId;

    @Column(nullable = false)
    private Long projectId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private boolean selected = false;

    public Candidate() {

    }

    public Candidate(UUID developerId, Long projectId, String firstName, String lastName, String description) {
        this.developerId = developerId;
        this.projectId = projectId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
    }

    public UUID getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(UUID candidateId) {
        this.candidateId = candidateId;
    }

    public UUID getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(UUID developerId) {
        this.developerId = developerId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
