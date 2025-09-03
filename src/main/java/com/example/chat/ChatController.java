package com.example.chat;


import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;


@Controller
public class ChatController {


@MessageMapping("/chat")
@SendTo("/topic/messages")
public ChatMessage handle(ChatMessage in) {
if (in.getSender() == null || in.getSender().isBlank()) {
in.setSender("anonymous");
}
in.setTimestamp(System.currentTimeMillis());
return in; // broadcast to all subscribers
}
}
