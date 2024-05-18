package com.seoultech.capstone.domain.chat.service;

import com.seoultech.capstone.domain.chat.Enum.SenderType;
import com.seoultech.capstone.domain.chat.dto.request.ChatRequest;
import com.seoultech.capstone.domain.chat.dto.response.ChatResponse;
import com.seoultech.capstone.domain.chat.entity.ChatMessage;
import com.seoultech.capstone.domain.chat.entity.ChatRoom;
import com.seoultech.capstone.domain.chat.repository.ChatMessageRepository;
import com.seoultech.capstone.domain.chat.repository.ChatRoomRepository;
import com.seoultech.capstone.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.seoultech.capstone.domain.chat.Enum.SenderType.validateChatType;
import static com.seoultech.capstone.exception.ErrorCode.ENTITY_NOT_FOUND;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ChatService {

    private final ChatMessageRepository chatMessageRepository;
    private final SimpMessagingTemplate messagingTemplate;
    private final ChatRoomRepository chatRoomRepository;

    public ChatResponse recordHistory(final String chatRoomNo, final ChatRequest request) {

        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomNo)
                .orElseThrow(() -> new CustomException(ENTITY_NOT_FOUND, "No such chatroom with id " +  chatRoomNo));

        SenderType senderType = validateChatType(request.getSenderType());

        final ChatMessage savedChatMessage = chatMessageRepository.save(
                ChatMessage.of(chatRoomNo, senderType, request.getContent()));

        ChatResponse response = ChatResponse.of(savedChatMessage);


        // 메시징 템플릿을 사용하여 메시지를 /sub/chatlist/{studentTaskProgressId}로 보냄
        messagingTemplate.convertAndSend("/sub/progressId/" + chatRoom.getStudentTaskProgressId(), response);

        return response;
    }

}
