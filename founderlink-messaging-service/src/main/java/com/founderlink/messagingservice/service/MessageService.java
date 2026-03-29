package com.founderlink.messagingservice.service;

import com.founderlink.messagingservice.entity.Message;
import com.founderlink.messagingservice.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository repo;

    private String buildConversationId(Long a, Long b) {
        return Math.min(a, b) + "_" + Math.max(a, b);
    }

    public Message send(Message msg) {
        msg.setConversationId(
                buildConversationId(msg.getSenderId(), msg.getReceiverId())
        );
        return repo.save(msg);
    }

    public List<Message> getHistory(String conversationId) {
        return repo.findByConversationIdOrderByCreatedAtAsc(conversationId);
    }

    public List<Message> getBetween(Long userId1, Long userId2) {
        return getHistory(buildConversationId(userId1, userId2));
    }
}