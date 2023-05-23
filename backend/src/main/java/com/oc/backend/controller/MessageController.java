package com.oc.backend.controller;

import com.oc.backend.model.Message;
import com.oc.backend.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/messages")
public class MessageController {
  @Autowired
  private MessageService messageService;
  @PostMapping
  public Message addNewMessage (@RequestBody Message newMessage){
    return messageService.addNewMessage(newMessage);

  }
}
