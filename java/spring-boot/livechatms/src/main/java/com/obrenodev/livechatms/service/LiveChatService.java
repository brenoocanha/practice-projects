package com.obrenodev.livechatms.service;

import com.obrenodev.livechatms.model.Message;
import com.obrenodev.livechatms.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LiveChatService {
    @Autowired
    private MessageRepository messageRepository;

    public List<Message> getMessages() {
        return messageRepository.findAll();
    }

    public Message createMessage(Message message) {
        message.setDate(new Date());
        return messageRepository.save(message);
    }
}
