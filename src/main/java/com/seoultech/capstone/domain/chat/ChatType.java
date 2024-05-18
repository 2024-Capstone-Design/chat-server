package com.seoultech.capstone.domain.chat;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.seoultech.capstone.exception.CustomException;
import com.seoultech.capstone.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ChatType {
    BOT("BOT"),
    STUDENT("STUDENT");

    private final String description;

    @JsonCreator
    public static ChatType validateChatType(String type) {
        for (ChatType chatType : ChatType.values()) {
            if (chatType.getDescription().equals(type)) {
                return chatType;
            }
        }
        throw new CustomException(ErrorCode.INVALID_REQUEST, "유효하지 않은 ChatType 입니다.");
    }
}
