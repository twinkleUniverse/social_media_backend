package com.apiwiz.SocialMedia.service;

import com.apiwiz.SocialMedia.dto.RequestDTO.group_request_dto.GroupRequestDto;
import com.apiwiz.SocialMedia.dto.ResponseDTO.group_response_dto.GroupResponseDto;
import com.apiwiz.SocialMedia.exception.userExceptions.UserNotExist;

public interface GroupService {
    public GroupResponseDto createGroup(String group_name, String admin_name) throws UserNotExist;
    public GroupResponseDto addMember(GroupRequestDto groupRequestDto) throws UserNotExist;
}
