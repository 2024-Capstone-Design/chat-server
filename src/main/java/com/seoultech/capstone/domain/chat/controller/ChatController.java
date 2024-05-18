package com.seoultech.capstone.domain.chat.controller;

import com.seoultech.capstone.domain.chat.dto.request.ChatRequest;
import com.seoultech.capstone.domain.chat.dto.response.ChatResponse;
import com.seoultech.capstone.domain.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @MessageMapping("/chat/{roomNo}")
    @SendTo("/sub/chat/{roomNo}")
    public ChatResponse broadcasting(final ChatRequest request,
                                     @DestinationVariable(value = "roomNo") final String chatRoomId)  {

        return chatService.recordHistory(chatRoomId, request);
    }

}
