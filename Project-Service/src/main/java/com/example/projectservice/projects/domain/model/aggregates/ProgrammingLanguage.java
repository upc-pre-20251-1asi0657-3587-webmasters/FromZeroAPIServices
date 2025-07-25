package com.example.projectservice.projects.domain.model.aggregates;

import com.example.projectservice.shared.model.aggregates.AuditableAbstractAggregateRoot;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.List;

@Entity

public class ProgrammingLanguage extends AuditableAbstractAggregateRoot<ProgrammingLanguage> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @ManyToMany(mappedBy = "languages")
    @JsonBackReference
    private List<Project> projects;

    public ProgrammingLanguage(String name){
        this.name=name;
    }
    public ProgrammingLanguage(){

    }

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
    public List<Project> getProjects() {
        return projects;
    }
    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

}
