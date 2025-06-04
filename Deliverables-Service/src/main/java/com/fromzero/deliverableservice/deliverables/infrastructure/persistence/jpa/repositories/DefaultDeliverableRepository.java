package com.fromzero.deliverableservice.deliverables.infrastructure.persistence.jpa.repositories;


import com.fromzero.deliverableservice.deliverables.domain.model.aggregates.DefaultDeliverable;
import com.fromzero.deliverableservice.deliverables.domain.valueobjects.ProjectTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DefaultDeliverableRepository extends JpaRepository<DefaultDeliverable, Long> {

    List<DefaultDeliverable> findByProjectTypeEnum(ProjectTypeEnum projectTypeEnum);

    int countByProjectTypeEnum(ProjectTypeEnum projectTypeEnum);
}
