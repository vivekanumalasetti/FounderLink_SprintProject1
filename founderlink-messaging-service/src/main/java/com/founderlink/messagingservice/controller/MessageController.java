package com.founderlink.messagingservice.controller;

import com.founderlink.messagingservice.entity.Message;
import com.founderlink.messagingservice.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping
    public ResponseEntity<Message> send(
            @RequestBody Message message,
            @RequestHeader("X-User-Id") Long senderId) {

        message.setSenderId(senderId);
        return ResponseEntity.ok(messageService.send(message));
    }

    @GetMapping("/conversation/{conversationId}")
    public ResponseEntity<List<Message>> getHistory(
            @PathVariable String conversationId) {

        return ResponseEntity.ok(messageService.getHistory(conversationId));
    }

    @GetMapping("/between")
    public ResponseEntity<List<Message>> getBetween(
            @RequestParam Long userId1,
            @RequestParam Long userId2) {

        return ResponseEntity.ok(messageService.getBetween(userId1, userId2));
    }
}