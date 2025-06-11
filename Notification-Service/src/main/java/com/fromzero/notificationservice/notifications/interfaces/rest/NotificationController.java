package com.fromzero.notificationservice.notifications.interfaces.rest;

import com.fromzero.notificationservice.notifications.domain.model.aggregates.Notification;
import com.fromzero.notificationservice.notifications.domain.model.queries.GetAllNotificationsQuery;
import com.fromzero.notificationservice.notifications.domain.services.NotificationCommandService;
import com.fromzero.notificationservice.notifications.domain.services.NotificationQueryService;
import com.fromzero.notificationservice.notifications.interfaces.rest.resources.NotificationResource;
import com.fromzero.notificationservice.notifications.interfaces.rest.transform.NotificationResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/v2/notifications")
@Tag(name="Notifications", description="Notifications management endpoint")
public class NotificationController {
    private final NotificationCommandService notificationCommandService;
    private final NotificationQueryService notificationQueryService;

    public NotificationController(NotificationCommandService notificationCommandService, NotificationQueryService notificationQueryService) {
        this.notificationCommandService = notificationCommandService;
        this.notificationQueryService = notificationQueryService;
    }

    @Operation(summary = "Get Notifications history")
    @GetMapping
    public ResponseEntity<List<NotificationResource>> getNotificationHistory() {
        var getAllNotificationsQuery = new GetAllNotificationsQuery();
        var notifications = this.notificationQueryService.handle(getAllNotificationsQuery);
        var notificationsResource = notifications.stream()
                .map(NotificationResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(notificationsResource);
    }

}