package com.apiwiz.SocialMedia.transformer;

import com.apiwiz.SocialMedia.dto.ResponseDTO.frndrequest_response_dto.FrndRequestResponseDto;
import com.apiwiz.SocialMedia.model.FriendRequest;

public class FriendRequestTransformer {
    public static  FrndRequestResponseDto frndRequestToResponseDto(FriendRequest friendRequest){
        return FrndRequestResponseDto.builder()
                .sender_name(friendRequest.getRequest_sender().getUsername())
                .status(friendRequest.getStatus())
                .build();
    }
}
