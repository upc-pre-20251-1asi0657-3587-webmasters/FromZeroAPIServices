package com.example.projectservice.projects.infrastructure.persistence.jpa.repositories;



import com.example.projectservice.projects.domain.model.aggregates.Project;
import com.example.projectservice.projects.domain.valueobjects.ProjectStateEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {
    List<Project> findAllByState(ProjectStateEnum state);
    List<Project> findAllByDeveloper(Long developer);
    List<Project> findAllByEnterprise(Long enterprise);

//    @Query("SELECT c FROM Candidate c WHERE c.project.id = :projectId")
//    List<Candidate> findAllCandidatesByProjectId(@Param("projectId") Long projectId);
}
