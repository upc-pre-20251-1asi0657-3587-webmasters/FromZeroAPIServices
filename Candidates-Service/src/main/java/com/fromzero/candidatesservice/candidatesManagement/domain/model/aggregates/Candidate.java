package com.fromzero.candidatesservice.candidatesManagement.domain.model.aggregates;



import com.fromzero.candidatesservice.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Setter
@Getter
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
}
