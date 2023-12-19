package com.apiwiz.SocialMedia.controller;

import com.apiwiz.SocialMedia.dto.RequestDTO.message_request_dto.MessageGroupRequestDto;
import com.apiwiz.SocialMedia.dto.RequestDTO.message_request_dto.MessageRequestDto;
import com.apiwiz.SocialMedia.dto.ResponseDTO.message_response_dto.MessageGroupResponseDto;
import com.apiwiz.SocialMedia.dto.ResponseDTO.message_response_dto.MessageResponseDto;
import com.apiwiz.SocialMedia.exception.userExceptions.UserNotExist;
import com.apiwiz.SocialMedia.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Message")
public class MessageController {
    @Autowired
    MessageService messageService;
    @PostMapping("/message")
    public ResponseEntity messageSent(@RequestBody MessageRequestDto messageRequestDto) throws UserNotExist {
        try {
            MessageResponseDto messageResponseDto = messageService.sendMessage(messageRequestDto);
            return new ResponseEntity<>(messageResponseDto, HttpStatus.OK);
        }catch (UserNotExist e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/send_group_message")
    public ResponseEntity groupMessageSent(@RequestBody MessageGroupRequestDto messageGroupRequestDto) throws UserNotExist {
        try {
            MessageGroupResponseDto messageResponseDto = messageService.sendGroupMessage(messageGroupRequestDto);
            return new ResponseEntity<>(messageResponseDto, HttpStatus.OK);
        }catch (UserNotExist e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
