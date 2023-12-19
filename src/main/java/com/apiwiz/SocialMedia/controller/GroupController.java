package com.apiwiz.SocialMedia.controller;

import com.apiwiz.SocialMedia.dto.RequestDTO.group_request_dto.GroupRequestDto;
import com.apiwiz.SocialMedia.dto.ResponseDTO.group_response_dto.GroupResponseDto;
import com.apiwiz.SocialMedia.exception.userExceptions.UserNotExist;
import com.apiwiz.SocialMedia.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Group")
public class GroupController {
    @Autowired
    GroupService groupService;
    @PostMapping("/create_group")
    public ResponseEntity create_group(@RequestParam("group_name")String groupname,@RequestParam("admin_name")String admin_name)
        throws UserNotExist{
        try{
            GroupResponseDto groupResponseDto=groupService.createGroup(groupname,admin_name);
            return new ResponseEntity<>(groupResponseDto, HttpStatus.CREATED);
        }catch (UserNotExist e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/create_group")
    public ResponseEntity addMember(@RequestBody GroupRequestDto groupRequestDto)
            throws UserNotExist{
        try{
            GroupResponseDto groupResponseDto=groupService.addMember(groupRequestDto);
            return new ResponseEntity<>(groupResponseDto, HttpStatus.OK);
        }catch (UserNotExist e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
