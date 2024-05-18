package com.seoultech.capstone.domain.chat.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChatRequest{
    public String content;
    public String senderType;
}
