package com.fromzero.notificationservice.notifications.infrastructure.persistence.jpa.repositories;

import com.fromzero.notificationservice.notifications.domain.model.aggregates.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NotificationRepository extends JpaRepository<Notification, UUID> {
    List<Notification> findAllByUserID(UUID userID);
}
