package com.seoultech.capstone.domain.chat;

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
    private ChatType senderType;

    @Field("created_at")
    @CreatedDate
    private LocalDateTime createdAt;

    @Builder
    public ChatMessage(String chatRoomId, String message, ChatType senderType) {
        this.chatRoomId = chatRoomId;
        this.message = message;
        this.senderType = senderType;
    }
}
