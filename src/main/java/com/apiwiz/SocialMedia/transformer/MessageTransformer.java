package com.apiwiz.SocialMedia.transformer;

import com.apiwiz.SocialMedia.dto.RequestDTO.message_request_dto.MessageGroupRequestDto;
import com.apiwiz.SocialMedia.dto.RequestDTO.message_request_dto.MessageRequestDto;
import com.apiwiz.SocialMedia.dto.ResponseDTO.message_response_dto.MessageGroupResponseDto;
import com.apiwiz.SocialMedia.dto.ResponseDTO.message_response_dto.MessageResponseDto;
import com.apiwiz.SocialMedia.model.Group;
import com.apiwiz.SocialMedia.model.Message;
import com.apiwiz.SocialMedia.model.User;

public class MessageTransformer {
    public static Message messageRequestDtoToMessage(MessageRequestDto messageRequestDto, User sender, User receiver){
        return Message.builder()
                .Content(messageRequestDto.getContent())
                .sender(sender)
                .receiver(receiver)
                .build();
    }

    public static Message mesageGroupRequestDtoToMessage(MessageGroupRequestDto messageGroupRequestDto, User sender, Group group){
        return Message.builder()
                .Content(messageGroupRequestDto.getContent())
                .sender(sender)
                .group_msg(group)
                .build();
    }

    public static MessageGroupResponseDto messageToGroupResponseDto(Message message){
        return MessageGroupResponseDto.builder()
                .message(message.getContent())
                .group_name(message.getGroup_msg().getGroup_name())
                .sendername(message.getSender().getUsername())
                .sent_date(message.getSent_date())
                .build();
    }

    public static MessageResponseDto messageToResponseDto(Message message){
        return MessageResponseDto.builder()
                .message(message.getContent())
                .receiver_name(message.getReceiver().getUsername())
                .sender_name(message.getSender().getUsername())
                .sent_date(message.getSent_date())
                .build();
    }
}
