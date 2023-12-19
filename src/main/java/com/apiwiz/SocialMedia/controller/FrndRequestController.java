package com.apiwiz.SocialMedia.controller;

import com.apiwiz.SocialMedia.dto.ResponseDTO.frndrequest_response_dto.FrndRequestResponseDto;
import com.apiwiz.SocialMedia.exception.userExceptions.UserNotExist;
import com.apiwiz.SocialMedia.model.FriendRequest;
import com.apiwiz.SocialMedia.service.FrndRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/Friend_Request")
public class FrndRequestController {
    @Autowired
    FrndRequestService frndRequestService;
    @PostMapping("/send_friend_request")
    public ResponseEntity sendFriendRequest(@RequestParam("sender_name")String sender,@RequestParam("receiver_name")String receiver)
        throws UserNotExist{
        try{
            String message=frndRequestService.sendRequest(sender,receiver);
            return new ResponseEntity<>(message, HttpStatus.OK);
        }catch (UserNotExist e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("get_all_friendrequest")
    public ResponseEntity allFriendRequest(@RequestParam("username") String name){
        Set<FrndRequestResponseDto> requestList=frndRequestService.getAllrequest(name);
        return new ResponseEntity<>(requestList,HttpStatus.OK);
    }

    @PutMapping("/accept_request")
    public ResponseEntity acceptRequest(@RequestParam("username")String username,@RequestParam("sender_name")String sender_name)
    throws UserNotExist{
        try {
            FrndRequestResponseDto frndRequestResponseDto = frndRequestService.accept(username, sender_name);
            return new ResponseEntity(frndRequestResponseDto, HttpStatus.OK);
        }catch (UserNotExist e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/decline_request")
    public ResponseEntity notAccept(@RequestParam("username")String username,@RequestParam("sender_name")String sender_name)
            throws UserNotExist{
        try {
            FrndRequestResponseDto frndRequestResponseDto = frndRequestService.notAccept(username, sender_name);
            return new ResponseEntity(frndRequestResponseDto, HttpStatus.OK);
        }catch (UserNotExist e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
      @PutMapping("/follow_back")
    public ResponseEntity followBack(@RequestParam("username")String username,@RequestParam("sender_name")String sender_name)
            throws UserNotExist{
        try {
            FrndRequestResponseDto frndRequestResponseDto = frndRequestService.followBack(username, sender_name);
            return new ResponseEntity(frndRequestResponseDto, HttpStatus.OK);
        }catch (UserNotExist e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get_number_of_followers")
    public ResponseEntity getNumberOfFollowers(@RequestParam("username")String username)
            throws UserNotExist{
        try {
            long numbers = frndRequestService.numberOfFollowers(username);
            return new ResponseEntity(numbers, HttpStatus.OK);
        }catch (UserNotExist e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get_number_of_followings")
    public ResponseEntity getNumberOfFollowings(@RequestParam("username")String username)
            throws UserNotExist{
        try {
            long numbers = frndRequestService.numberOfFollowings(username);
            return new ResponseEntity(numbers, HttpStatus.OK);
        }catch (UserNotExist e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
