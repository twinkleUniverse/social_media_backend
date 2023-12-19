package com.apiwiz.SocialMedia.controller;

import com.apiwiz.SocialMedia.dto.RequestDTO.post_request_dto.PostRequestDto;
import com.apiwiz.SocialMedia.dto.RequestDTO.post_request_dto.RepostRequestDto;
import com.apiwiz.SocialMedia.dto.ResponseDTO.post_response_dto.PostResponseDto;
import com.apiwiz.SocialMedia.exception.postExceptions.PostNotFound;
import com.apiwiz.SocialMedia.exception.userExceptions.UserNotExist;
import com.apiwiz.SocialMedia.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Post")
public class PostController {

    @Autowired
    PostService postService;
    @PostMapping("/post_content")
    public ResponseEntity postContent(@RequestBody PostRequestDto postRequestDto){
        PostResponseDto postResponseDto = postService.post_content(postRequestDto);
        return new ResponseEntity<>(postResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/get_all_post")
    public ResponseEntity getAllPost(@RequestParam("username") String username) throws UserNotExist {
        try {
            List<PostResponseDto> postlist = postService.allPostList(username);
            return new ResponseEntity<>(postlist,HttpStatus.OK);

        }catch(UserNotExist e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("delete_post")
    public ResponseEntity deletePost(@RequestParam("post_id") long post_id) throws PostNotFound {
        try{
            String message=postService.deletePost(post_id);
            return new ResponseEntity(message,HttpStatus.OK);
        }catch (PostNotFound e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("repost")
    public ResponseEntity repost_the_post(@RequestBody RepostRequestDto RepostRequestDto) throws UserNotExist,PostNotFound{
        try {
            PostResponseDto postResponseDto = postService.rePost(RepostRequestDto);
            return new ResponseEntity<>(postResponseDto,HttpStatus.OK);
        }catch (UserNotExist e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }catch (PostNotFound e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
