package com.apiwiz.SocialMedia.controller;

import com.apiwiz.SocialMedia.dto.ResponseDTO.post_response_dto.PostResponseDto;
import com.apiwiz.SocialMedia.exception.postExceptions.PostNotFound;
import com.apiwiz.SocialMedia.exception.userExceptions.UserNotExist;
import com.apiwiz.SocialMedia.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/event")
public class EventController {
     @Autowired
    EventService eventService;
    @PostMapping("/like_post")
    public ResponseEntity like_post(@RequestParam("post_id")long post_id,@RequestParam("liked_username")String likedUsername ) throws PostNotFound {
        try{
            PostResponseDto postResponseDto=eventService.likeThePost(post_id,likedUsername);
             return new ResponseEntity<>(postResponseDto,HttpStatus.OK);
        }catch (PostNotFound e){
            return  new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }
    @PostMapping("/share_post")
    public ResponseEntity sharePost(@RequestParam("post_id")long post_id,@RequestParam("sendername")String sender,@RequestParam("receivername")String reciver)
            throws UserNotExist,PostNotFound{
        try{
            String msg=eventService.sharePost(post_id,sender,reciver);
            return new ResponseEntity<>(msg,HttpStatus.OK);
        }catch (PostNotFound e){
            return  new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }catch (UserNotExist e){
            return  new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("dislike_post")
    public ResponseEntity dislike_Post(@RequestParam("post_id") long post_id,@RequestParam("likedUserName")String name) throws PostNotFound {
        try{
            String message=eventService.dislike(post_id,name);
            return new ResponseEntity(message,HttpStatus.OK);
        }catch (PostNotFound e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/numberOfLikes")
    public ResponseEntity number_of_likes(@RequestParam("post_id") long post_id)throws PostNotFound{
        try{
            int likes=eventService.getPostlikes(post_id);
            return new ResponseEntity(likes,HttpStatus.OK);
        }catch (PostNotFound e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
