package com.apiwiz.SocialMedia.service;

import com.apiwiz.SocialMedia.dto.RequestDTO.message_request_dto.MessageGroupRequestDto;
import com.apiwiz.SocialMedia.dto.RequestDTO.message_request_dto.MessageRequestDto;
import com.apiwiz.SocialMedia.dto.ResponseDTO.message_response_dto.MessageGroupResponseDto;
import com.apiwiz.SocialMedia.dto.ResponseDTO.message_response_dto.MessageResponseDto;
import com.apiwiz.SocialMedia.exception.userExceptions.UserNotExist;
import org.springframework.web.bind.annotation.RequestBody;

public interface MessageService {
    public MessageResponseDto sendMessage(MessageRequestDto messageRequestDto) throws UserNotExist;
   public MessageGroupResponseDto sendGroupMessage(@RequestBody MessageGroupRequestDto messageGroupRequestDto) throws UserNotExist;
}
