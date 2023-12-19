package com.apiwiz.SocialMedia.service.impl;

import com.apiwiz.SocialMedia.dto.RequestDTO.message_request_dto.MessageGroupRequestDto;
import com.apiwiz.SocialMedia.dto.RequestDTO.message_request_dto.MessageRequestDto;
import com.apiwiz.SocialMedia.dto.ResponseDTO.message_response_dto.MessageGroupResponseDto;
import com.apiwiz.SocialMedia.dto.ResponseDTO.message_response_dto.MessageResponseDto;
import com.apiwiz.SocialMedia.exception.userExceptions.UserNotExist;
import com.apiwiz.SocialMedia.model.Group;
import com.apiwiz.SocialMedia.model.Message;
import com.apiwiz.SocialMedia.model.User;
import com.apiwiz.SocialMedia.repository.GroupRepository;
import com.apiwiz.SocialMedia.repository.MessageRepository;
import com.apiwiz.SocialMedia.repository.UserRepository;
import com.apiwiz.SocialMedia.service.MessageService;
import com.apiwiz.SocialMedia.transformer.MessageTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    GroupRepository groupRepository;
    public MessageResponseDto sendMessage(MessageRequestDto messageRequestDto) throws UserNotExist{
        User sender=userRepository.findByUsername(messageRequestDto.getSenderName()).get();
        User receiver=userRepository.findByUsername(messageRequestDto.getReceiverName()).get();
        if(!(sender.getFollowing().contains(receiver)&&sender.getFollowers().contains(receiver))){
            throw new UserNotExist("You can not message this person");
        }
        List<Message>sendermessageList=sender.getSendMsgList();
        Message message= MessageTransformer.messageRequestDtoToMessage(messageRequestDto,sender,receiver);

        sendermessageList.add(message);
        sender.setSendMsgList(sendermessageList);
        List<Message>receiveMessageList=receiver.getReceivedMsglist();
        receiveMessageList.add(message);
        receiver.setReceivedMsglist(receiveMessageList);
        Message savedmessage=messageRepository.save(message);
        return MessageTransformer.messageToResponseDto(savedmessage);
    }

    @Override
    public MessageGroupResponseDto sendGroupMessage(MessageGroupRequestDto messageGroupRequestDto) throws UserNotExist {
        User sender=userRepository.findByUsername(messageGroupRequestDto.getSenderName()).get();
        Group group=groupRepository.findById(messageGroupRequestDto.getGroup_id()).get();
        if(!group.getMembers().contains(sender)){
            throw new UserNotExist("You are not the part of this group");
        }
        List<Message>messageList=sender.getSendMsgList();
        Message message= MessageTransformer.mesageGroupRequestDtoToMessage(messageGroupRequestDto,sender,group);
        messageList.add(message);
        sender.setSendMsgList(messageList);

        List<Message>groupMessageList=group.getGroup_messages();
        groupMessageList.add(message);
        group.setGroup_messages(groupMessageList);
        Message savedmessage=messageRepository.save(message);

        return MessageTransformer.messageToGroupResponseDto(savedmessage);
    }

   /* public List<MessageResponseDto> getAllMessages(String username){

    }*/
}
