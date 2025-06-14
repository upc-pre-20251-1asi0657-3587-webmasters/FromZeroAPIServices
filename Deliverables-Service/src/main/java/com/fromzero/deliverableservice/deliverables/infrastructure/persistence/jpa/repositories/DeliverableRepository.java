package com.fromzero.deliverableservice.deliverables.infrastructure.persistence.jpa.repositories;


import com.fromzero.deliverableservice.deliverables.domain.model.aggregates.Deliverable;
import com.fromzero.deliverableservice.deliverables.domain.valueobjects.DeliverableStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeliverableRepository extends JpaRepository<Deliverable, Long> {
    List<Deliverable> findAllByProjectId(Long projectId);

    @Query("SELECT MAX(d.orderNumber) FROM Deliverable d WHERE d.projectId = :projectId")
    Integer findMaxOrderNumberByProject(@Param("projectId") Long projectId);

    Optional<Deliverable> findByProjectIdAndOrderNumber(Long projectId, int orderNumber);

    long countByProjectId(Long projectId);

    long countByProjectIdAndState(Long projectId, DeliverableStatus state);


    void deleteAllByProjectId(Long projectId);

}
