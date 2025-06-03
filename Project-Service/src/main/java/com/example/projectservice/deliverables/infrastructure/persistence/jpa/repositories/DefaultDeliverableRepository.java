package com.example.projectservice.deliverables.infrastructure.persistence.jpa.repositories;


import com.example.projectservice.deliverables.domain.model.aggregates.DefaultDeliverable;
import com.example.projectservice.projects.domain.valueobjects.ProjectTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DefaultDeliverableRepository extends JpaRepository<DefaultDeliverable, Long> {

    List<DefaultDeliverable> findByProjectTypeEnum(ProjectTypeEnum projectTypeEnum);

    int countByProjectTypeEnum(ProjectTypeEnum projectTypeEnum);
}
