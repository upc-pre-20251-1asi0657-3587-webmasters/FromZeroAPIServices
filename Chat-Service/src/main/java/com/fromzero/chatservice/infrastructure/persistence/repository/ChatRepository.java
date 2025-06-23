package com.fromzero.chatservice.infrastructure.persistence.repository;

import com.fromzero.chatservice.domain.model.aggregates.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

public interface ChatRepository extends JpaRepository<Chat, UUID> {
    Optional<Chat> findByProjectId(Long projectId);
    Boolean existsByProjectId(Long projectId);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN TRUE ELSE FALSE END " +
            "FROM Chat c " +
            "WHERE c.projectId = :projectId AND (c.user1Id = :userId OR c.user2Id = :userId)")
    Boolean existsByProjectIdAndUserId(@Param("projectId") Long projectId, @Param("userId") UUID userId);
    Boolean existsByUser1IdAndProjectId(UUID userId, Long projectId);
    Boolean existsByUser2IdAndProjectId(UUID userId, Long projectId);
}
