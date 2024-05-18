package com.seoultech.capstone.config;

import com.seoultech.capstone.domain.auth.jwt.TokenProvider;
import com.seoultech.capstone.domain.common.user.student.StudentRepository;
import com.seoultech.capstone.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import static com.seoultech.capstone.exception.ErrorCode.ENTITY_NOT_FOUND;

@Component
@RequiredArgsConstructor
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE + 99)
public class StompHandler implements ChannelInterceptor {

    private final TokenProvider tokenProvider;
    private final StudentRepository studentRepository;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {

        final StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);

        String sessionId = accessor.getSessionId();

        if (StompCommand.CONNECT == accessor.getCommand()) {

            final String authorization = tokenProvider.resolveSocketToken(accessor);
            tokenProvider.validateToken(authorization);

            Authentication authentication = tokenProvider.resolveToken(authorization);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                studentRepository.findById(Integer.valueOf(authentication.getName()))
                        .orElseThrow(() -> new CustomException(ENTITY_NOT_FOUND, "No such student with id " + authentication.getName()));
            }

        }
        return MessageBuilder.fromMessage(message).setHeader("sessionId", sessionId).build();
    }

}
