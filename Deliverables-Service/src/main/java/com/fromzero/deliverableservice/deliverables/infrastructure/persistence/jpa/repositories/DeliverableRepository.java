package com.fromzero.deliverableservice.deliverables.infrastructure.persistence.jpa.repositories;


import com.fromzero.deliverableservice.deliverables.domain.model.aggregates.Deliverable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeliverableRepository extends JpaRepository<Deliverable, Long> {
    List<Deliverable> findAllByProjectId(String projectId);

    @Query("SELECT MAX(d.orderNumber) FROM Deliverable d WHERE d.projectId = :projectId")
    Integer findMaxOrderNumberByProject(@Param("projectId") String projectId);

    Optional<Deliverable> findByProjectIdAndOrderNumber(String projectId, int orderNumber);

    void deleteAllByProjectId(String projectId);

}
