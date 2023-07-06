package com.oc.backend.service;

import com.oc.backend.model.Message;
import com.oc.backend.repository.MessageRepository;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

  private final MessageRepository messageRepository;

  public MessageService(MessageRepository messageRepository) {
    this.messageRepository = messageRepository;
  }

  public Message addNewMessage(Message newMessage) {
    return messageRepository.save(newMessage);
  }
}
