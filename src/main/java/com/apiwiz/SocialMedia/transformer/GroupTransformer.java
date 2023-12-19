package com.apiwiz.SocialMedia.transformer;

import com.apiwiz.SocialMedia.dto.RequestDTO.group_request_dto.GroupRequestDto;
import com.apiwiz.SocialMedia.dto.ResponseDTO.group_response_dto.GroupResponseDto;
import com.apiwiz.SocialMedia.model.Group;
import com.apiwiz.SocialMedia.model.User;

public class GroupTransformer {
    public static Group groupRequestDtoTogroup(GroupRequestDto groupRequestDto, User user){
        return Group.builder()
                .group_name(groupRequestDto.getGroup_name())
                .admin(user)
                .build();
    }
    public static GroupResponseDto groupToGroupResponseDto(Group group){
        return GroupResponseDto.builder()
                .group_name(group.getGroup_name())
                .admin_name(group.getAdmin().getUsername())
                .membersSet(group.getMembers())
                .build();
    }
}
