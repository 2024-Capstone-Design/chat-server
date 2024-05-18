package com.seoultech.capstone.domain.chat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Document(collection = "chat_rooms")
@Getter
@NoArgsConstructor
@ToString
public class ChatRoom {
    @Id
    private ObjectId id;
    @Field("student_task_progress_id")
    private Integer studentTaskProgressId;
    @Field("created_at")
    @CreatedDate
    private LocalDateTime createdAt;

}
