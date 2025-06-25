package com.fromzero.chatservice.interfaces.rest;

import com.fromzero.chatservice.domain.model.aggregates.Chat;
import com.fromzero.chatservice.domain.model.queries.GetChatByProjectIdQuery;
import com.fromzero.chatservice.domain.services.ChatCommandService;
import com.fromzero.chatservice.domain.services.ChatQueryService;
import com.fromzero.chatservice.interfaces.rest.resources.CreateChatResource;
import com.fromzero.chatservice.interfaces.rest.transform.CreateChatCommandFromResourceAssembler;
import com.fromzero.chatservice.interfaces.rest.transform.CreateChatResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value="/api/v1/chats", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Chats", description = "Available chat endpoints")
public class ChatController {
    private final ChatQueryService chatQueryService;
    private final ChatCommandService chatCommandService;

    public ChatController(ChatQueryService chatQueryService, ChatCommandService chatCommandService) {
        this.chatQueryService = chatQueryService;
        this.chatCommandService = chatCommandService;
    }

    @PostMapping("/")
    public ResponseEntity<CreateChatResource> createChat(@RequestBody CreateChatResource createChatResource) {
        var createChatCommand = CreateChatCommandFromResourceAssembler.toCommandFromResource(createChatResource);
        var chat = this.chatCommandService.handle(createChatCommand);
        var chatResource = CreateChatResourceFromEntityAssembler.toResourceFromEntity(chat.get());

        return new ResponseEntity<>(chatResource, HttpStatus.CREATED);
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<CreateChatResource> getChatByProjectId(@PathVariable Long projectId) {
        var getChatByProjectIdQuery = new GetChatByProjectIdQuery(projectId);
        var chat = this.chatQueryService.handle(getChatByProjectIdQuery);
        if (chat.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var chatResource = CreateChatResourceFromEntityAssembler.toResourceFromEntity(chat.get());
        return ResponseEntity.ok(chatResource);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Optional<List<Chat>>> getChatsByUserId(@PathVariable UUID userId) {
        var chats = this.chatQueryService.getChatsByUserId(userId);
        if (chats.isEmpty()) {
            return ResponseEntity.notFound().build();
        };
        return ResponseEntity.ok(chats);
    }

}
