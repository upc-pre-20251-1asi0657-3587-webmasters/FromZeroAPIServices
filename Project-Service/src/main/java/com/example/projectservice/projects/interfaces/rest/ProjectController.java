package com.example.projectservice.projects.interfaces.rest;

import com.example.projectservice.projects.domain.model.aggregates.Framework;
import com.example.projectservice.projects.domain.model.aggregates.ProgrammingLanguage;
import com.example.projectservice.projects.domain.model.commands.CreateProjectCommand;
import com.example.projectservice.projects.domain.model.commands.DeleteProjectCommand;
import com.example.projectservice.projects.domain.model.queries.*;
import com.example.projectservice.projects.domain.services.FrameworksQueryService;
import com.example.projectservice.projects.domain.services.ProgrammingLanguagesQueryService;
import com.example.projectservice.projects.domain.services.ProjectCommandService;
import com.example.projectservice.projects.domain.services.ProjectQueryService;
import com.example.projectservice.projects.interfaces.acl.ProfileContextFacade;
import com.example.projectservice.projects.interfaces.rest.resources.CreateProjectResource;
import com.example.projectservice.projects.interfaces.rest.resources.ProjectResource;
import com.example.projectservice.projects.interfaces.rest.transform.CreateProjectResourceFromEntityAssembler;
import com.example.projectservice.projects.interfaces.rest.transform.ProjectResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/projects", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Projects", description = "Projects Management Endpoints")
public class ProjectController {
    private final ProjectCommandService projectCommandService;
    private final ProjectQueryService projectQueryService;
    private final ProgrammingLanguagesQueryService programmingLanguagesQueryService;
    private final FrameworksQueryService frameworksQueryService;
    private final ProfileContextFacade profileContextFacade;

    public ProjectController(ProjectCommandService projectCommandService,
                             ProjectQueryService projectQueryService,
                             ProgrammingLanguagesQueryService programmingLanguagesQueryService,
                             FrameworksQueryService frameworksQueryService,
                             ProfileContextFacade profileContextFacade) {
        this.projectCommandService = projectCommandService;
        this.projectQueryService = projectQueryService;
        this.programmingLanguagesQueryService = programmingLanguagesQueryService;
        this.frameworksQueryService = frameworksQueryService;
        this.profileContextFacade = profileContextFacade;
    }

    public List<ProgrammingLanguage> getProgrammingLanguages(List<Long>languages) {
        List<Optional<ProgrammingLanguage>> optionalProgrammingLanguages = languages.stream()
                .map(item -> {
                    var getLanguageById = new GetProgrammingLanguageByIdQuery(item);
                    return this.programmingLanguagesQueryService.handle(getLanguageById);
                })
                .toList();

        // Convertir los Optional a ProgrammingLanguage
        List<ProgrammingLanguage> programmingLanguages = optionalProgrammingLanguages.stream()
                .map(Optional::get)
                .collect(Collectors.toList());
        return programmingLanguages;
    }

    public List<Framework> getFrameworks(List<Long> frameworks){
        List<Optional<Framework>> optionalFrameworks = frameworks.stream()
                .map(item -> {
                    var getFrameworkById = new GetFrameworkByIdQuery(item);
                    return this.frameworksQueryService.handle(getFrameworkById);
                })
                .toList();

        List<Framework> frameworksList=optionalFrameworks.stream()
                .map(Optional::get)
                .collect(Collectors.toList());
        return frameworksList;
    }

    @Operation(summary = "Create project")
    @PostMapping
    public ResponseEntity<CreateProjectResource> createProject(@RequestBody CreateProjectResource resource) {
        // NOTE: For the moment we are not validating the ownerId
//        var enterprise = this.profileContextFacade.getEnterpriseByUserId(resource.ownerId());
//        if (enterprise == null) return ResponseEntity.badRequest().build();
        var programmingLanguages = getProgrammingLanguages(resource.languages());
        var frameworks=getFrameworks(resource.frameworks());
        var createProjectCommand = new CreateProjectCommand(resource.name(), resource.description(), resource.ownerId(),
                programmingLanguages,frameworks,resource.type(),resource.budget(),resource.methodologies());
        var project = this.projectCommandService.handle(createProjectCommand);
        if (project.isEmpty()) return ResponseEntity.badRequest().build();
        var createProjectResource = CreateProjectResourceFromEntityAssembler.toResourceFromEntity(project.get());

        return new ResponseEntity<>(createProjectResource, HttpStatus.CREATED);
    }

    @Operation(summary = "Get All Projects")
    @GetMapping
    public ResponseEntity<List<ProjectResource>> getAllProjects() {
        var getAllProjectsQuery = new GetAllProjectsQuery();
        var projects = this.projectQueryService.handle(getAllProjectsQuery);
        var projectsResources = projects.stream()
                .map(ProjectResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(projectsResources);
    }

    @Operation(summary = "Get Project By Id")
    @GetMapping(value = "/{id}")
    public ResponseEntity<ProjectResource> getProjectById(@PathVariable Long id) {
        var getProjectByIdQuery = new GetProjectByIdQuery(id);
        var project = this.projectQueryService.handle(getProjectByIdQuery);
        if (project.isEmpty()) return ResponseEntity.badRequest().build();
        var projectResource = ProjectResourceFromEntityAssembler.toResourceFromEntity(project.get());
        return ResponseEntity.ok(projectResource);
    }

    @Operation(summary = "Get All Projects By Enterprise Id")
    @GetMapping(value = "/enterprise/{enterpriseUserId}")
    public ResponseEntity<List<ProjectResource>> getAllProjectsByEnterpriseId(@PathVariable String enterpriseUserId){
//        var enterprise = this.profileContextFacade.getEnterpriseByUserId(enterpriseUserId);
//        if(enterprise==null) return ResponseEntity.badRequest().build();
        var getProjectsByEnterpriseIdQuery = new GetAllProjectsByEnterpriseIdQuery(enterpriseUserId);
        var projects =this.projectQueryService.handle(getProjectsByEnterpriseIdQuery);
        var projectResources = projects.stream()
                .map(ProjectResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(projectResources);
    }

    @Operation(summary = "Delete a project by Id")
    @DeleteMapping(value = "/{projectId}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long projectId) {
        var getProjectByIdQuery = new GetProjectByIdQuery(projectId);
        var project = this.projectQueryService.handle(getProjectByIdQuery);
        if (project.isEmpty()) return ResponseEntity.badRequest().build();
        var deleteProjectCommand = new DeleteProjectCommand(project.get().getId());
        this.projectCommandService.handle(deleteProjectCommand);
        return ResponseEntity.noContent().build();
    }

}
