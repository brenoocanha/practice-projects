package com.obrenodev.livechatms.controller;

import com.obrenodev.livechatms.model.Message;
import com.obrenodev.livechatms.service.LiveChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class LiveChatController {
    @Autowired
    private LiveChatService liveChatService;

    @MessageMapping("/new-message")
    @SendToUser("/topics/livechatms")
    public ResponseEntity<Message> newMessage(Message input) {
        Message createdMessage = liveChatService.createMessage(input);
        return ResponseEntity.ok(createdMessage);
    }

    @GetMapping
    public ResponseEntity<List<Message>> getMessages() {
        return ResponseEntity.ok(liveChatService.getMessages());
    }
}
