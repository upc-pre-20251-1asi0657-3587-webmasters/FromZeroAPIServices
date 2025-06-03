package com.example.projectservice.projects.infrastructure.persistence.jpa.repositories;



import com.example.projectservice.projects.domain.model.entities.HighlightProject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HighlightProjectRepository extends JpaRepository<HighlightProject, Long> {
}
