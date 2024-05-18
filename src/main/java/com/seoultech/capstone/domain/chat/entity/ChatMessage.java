package com.seoultech.capstone.domain.chat.entity;

import com.seoultech.capstone.domain.chat.Enum.SenderType;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Getter
@Setter
@Document(collection = "chat_messages")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ChatMessage {

    @Id
    private ObjectId id;

    @Field("chat_room_id")
    private String chatRoomId;

    @Field("message")
    private String message;

    @Field("sender_type")
    private SenderType senderType;

    @Field("created_at")
    @CreatedDate
    private LocalDateTime createdAt;

    public String getId() {
        return String.valueOf(id);
    }

    @Builder
    public ChatMessage(String chatRoomId, String message, SenderType senderType, LocalDateTime createdAt) {
        this.chatRoomId = chatRoomId;
        this.message = message;
        this.senderType = senderType;
        this.createdAt = createdAt;
    }

    public static ChatMessage of(String chatRoomId, SenderType senderType, String message) {

        return ChatMessage.builder()
                .chatRoomId(chatRoomId)
                .senderType(senderType)
                .message(message)
                .build();
    }

}
