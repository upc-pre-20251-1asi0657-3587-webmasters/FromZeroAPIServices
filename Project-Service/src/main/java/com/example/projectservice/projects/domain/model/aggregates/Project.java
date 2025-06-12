package com.example.projectservice.projects.domain.model.aggregates;

import com.example.projectservice.projects.domain.model.commands.CreateProjectCommand;
import com.example.projectservice.projects.domain.state.*;
import com.example.projectservice.projects.domain.valueobjects.ProjectStateEnum;
import com.example.projectservice.projects.domain.valueobjects.ProjectTypeEnum;
import com.example.projectservice.shared.model.aggregates.AuditableAbstractAggregateRoot;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Project extends AuditableAbstractAggregateRoot<Project> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    private ProjectStateEnum state;

    private Double progress;

    @Column(nullable = false)
    private String enterprise;

    @Column(nullable = false)
    private Long developer;

    @Column
    private List<String> candidates = new ArrayList<>();

    // many-to-many relationship
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "project_programming_languages",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "programming_language_id")
    )
    @JsonManagedReference
    private List<ProgrammingLanguage> languages;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "project_frameworks",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "framework_id")
    )
    @JsonManagedReference
    private List<Framework> frameworks;

    @Column
    private ProjectTypeEnum type;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String budget;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String methodologies;

    public Project(CreateProjectCommand command){
        this.name = command.name();
        this.description = command.description();
        this.state = ProjectStateEnum.LOOKING_FOR_DEVELOPERS;
        this.progress = 0.0;
        this.enterprise = command.enterprise();
        this.developer = null;
        this.languages = new ArrayList<>();
        this.frameworks = new ArrayList<>();
        this.type = command.type();
        this.budget = command.budget();
        this.methodologies = command.methodologies();
    }

    public Project() {}

    @Transient
    public ProjectState getStateHandler() {
        return switch (this.state) {
            case LOOKING_FOR_DEVELOPERS -> new LookingForDeveloperState();
            case NOT_STARTED -> new NotStartedState();
            case IN_PROCESS -> new InProcessState();
            case COMPLETED -> new CompletedState();
        };
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProjectStateEnum getState() {
        return state;
    }

    public void setState(ProjectStateEnum state) {
        this.state = state;
    }

    public Double getProgress() {
        return progress;
    }

    public void setProgress(Double progress) {
        this.progress = progress;
    }

    public String getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(String enterprise) {
        this.enterprise = enterprise;
    }

    public Long getDeveloper() {
        return developer;
    }

    public void setDeveloper(Long developer) {
        this.developer = developer;
    }

    public List<String> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<String> candidates) {
        this.candidates = candidates;
    }

    public List<ProgrammingLanguage> getLanguages() {
        return languages;
    }

    public void setLanguages(List<ProgrammingLanguage> languages) {
        this.languages = languages;
    }

    public List<Framework> getFrameworks() {
        return frameworks;
    }

    public void setFrameworks(List<Framework> frameworks) {
        this.frameworks = frameworks;
    }

    public ProjectTypeEnum getType() {
        return type;
    }

    public void setType(ProjectTypeEnum type) {
        this.type = type;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getMethodologies() {
        return methodologies;
    }

    public void setMethodologies(String methodologies) {
        this.methodologies = methodologies;
    }
}
