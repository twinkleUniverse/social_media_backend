package com.apiwiz.SocialMedia.service;

import com.apiwiz.SocialMedia.dto.ResponseDTO.frndrequest_response_dto.FrndRequestResponseDto;
import com.apiwiz.SocialMedia.exception.userExceptions.UserNotExist;

import java.util.List;
import java.util.Set;

public interface FrndRequestService {
    public String sendRequest(String sender,String receiver) throws UserNotExist;
    public Set<FrndRequestResponseDto> getAllrequest(String name);
    public FrndRequestResponseDto accept(String username,String sender_name) throws UserNotExist;
    public FrndRequestResponseDto notAccept(String username,String sender_name) throws UserNotExist;

    public FrndRequestResponseDto followBack(String username,String sender_name) throws UserNotExist;
    public Long numberOfFollowers(String username) throws UserNotExist;
    public Long numberOfFollowings(String username) throws UserNotExist;
}
