package com.apiwiz.SocialMedia.service.impl;

import com.apiwiz.SocialMedia.dto.RequestDTO.group_request_dto.GroupRequestDto;
import com.apiwiz.SocialMedia.dto.ResponseDTO.group_response_dto.GroupResponseDto;
import com.apiwiz.SocialMedia.exception.userExceptions.UserNotExist;
import com.apiwiz.SocialMedia.model.Group;
import com.apiwiz.SocialMedia.model.User;
import com.apiwiz.SocialMedia.repository.GroupRepository;
import com.apiwiz.SocialMedia.repository.UserRepository;
import com.apiwiz.SocialMedia.service.GroupService;
import com.apiwiz.SocialMedia.transformer.GroupTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class GroupServiceImpl implements GroupService {
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    UserRepository userRepository;


    public GroupResponseDto createGroup(String group_name,String admin_name) throws UserNotExist{
        Optional<User> optionalUser=userRepository.findByUsername(admin_name);
        if(!optionalUser.isPresent()){
            throw new UserNotExist("User not found");
        }
        Group group=new Group();
        group.setGroup_name(group_name);
        group.setAdmin(optionalUser.get());
        Group savedGroup=groupRepository.save(group);
        return GroupTransformer.groupToGroupResponseDto(savedGroup);
    }
    public GroupResponseDto addMember(GroupRequestDto groupRequestDto) throws UserNotExist{
        Optional<User> optionalUser=userRepository.findByUsername(groupRequestDto.getAdd_user_name());
        Optional<Group>optionalGroup=groupRepository.findByGroupname(groupRequestDto.getGroup_name());
        if(!optionalUser.isPresent()){
            throw new UserNotExist("User not found");
        }
        if(!optionalGroup.isPresent()) throw new UserNotExist("No group present with this name");
        Group group=GroupTransformer.groupRequestDtoTogroup(groupRequestDto,optionalUser.get());
        Set<User> membersList=group.getMembers();
        membersList.add(optionalUser.get());
        group.setMembers(membersList);
        User user=optionalUser.get();
        Set<Group>group_list=user.getGroup_list();
        group_list.add(group);
        user.setGroup_list(group_list);
       User savedUser=userRepository.save(user);

        return GroupTransformer.groupToGroupResponseDto(group);
    }

}
