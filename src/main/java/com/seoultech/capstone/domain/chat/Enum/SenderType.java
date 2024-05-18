package com.seoultech.capstone.domain.chat.Enum;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.seoultech.capstone.exception.CustomException;
import com.seoultech.capstone.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SenderType {
    BOT("BOT"),
    STUDENT("STUDENT");

    private final String description;

    @JsonCreator
    public static SenderType validateChatType(String type) {
        for (SenderType senderType : SenderType.values()) {
            if (senderType.getDescription().equals(type)) {
                return senderType;
            }
        }
        throw new CustomException(ErrorCode.INVALID_REQUEST, "유효하지 않은 SenderType 입니다.");
    }
}
