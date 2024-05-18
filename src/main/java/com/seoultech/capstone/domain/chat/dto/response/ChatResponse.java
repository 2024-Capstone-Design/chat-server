package com.seoultech.capstone.domain.chat.dto.response;

import com.seoultech.capstone.domain.chat.Enum.SenderType;
import com.seoultech.capstone.domain.chat.entity.ChatMessage;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChatResponse{
    public String chatId;
    public String  roomId;
    public SenderType senderType;
    public String message;
    public LocalDateTime createAt;

    @Builder
    public ChatResponse(String chatId, String roomId, SenderType senderType, String message, LocalDateTime createAt) {
        this.chatId = chatId;
        this.roomId = roomId;
        this.senderType = senderType;
        this.message = message;
        this.createAt = createAt;
    }


    public static ChatResponse of(final ChatMessage entity) {
        return new ChatResponse(
                entity.getId(),
                entity.getChatRoomId(),
                entity.getSenderType(),
                entity.getMessage(),
                entity.getCreatedAt()
        );
    }
}
