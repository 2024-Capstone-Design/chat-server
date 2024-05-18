package com.seoultech.capstone.domain.chat.repository;

import com.seoultech.capstone.domain.chat.entity.ChatRoom;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRoomRepository extends MongoRepository<ChatRoom, String> {


}
