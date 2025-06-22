package com.fromzero.candidatesservice.candidatesManagement.domain.model.events;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class ChatRoomCreatedEvent {
    Long projectId;
    UUID ownerId;
    UUID developerId;
}
