package com.apiwiz.SocialMedia.controller;

import com.apiwiz.SocialMedia.dto.RequestDTO.comment_request_dto.CommentRequestDto;
import com.apiwiz.SocialMedia.dto.ResponseDTO.comment_response_dto.CommentsResponseDto;
import com.apiwiz.SocialMedia.dto.ResponseDTO.post_response_dto.PostResponseDto;
import com.apiwiz.SocialMedia.exception.postExceptions.PostNotFound;
import com.apiwiz.SocialMedia.service.CommentService;
import org.hibernate.annotations.Comments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    CommentService commentService;
    @PostMapping("/comment_post")
    public ResponseEntity comment_a_post(@RequestBody CommentRequestDto commentRequestDto) throws PostNotFound {
       try {
           PostResponseDto postResponseDto=commentService.commentOnPost(commentRequestDto);
           return new ResponseEntity<>(postResponseDto, HttpStatus.OK);
       }catch (PostNotFound p){
           return new ResponseEntity<>(p.getMessage(),HttpStatus.NOT_FOUND);
       }
    }

    @DeleteMapping("/delete_comment")
    public ResponseEntity comment_a_post(@RequestParam("comment_id") long comment_id,@RequestParam("post_id") long post_id) throws PostNotFound{
         try{
             String message=commentService.deleteComment(comment_id,post_id);
              return new ResponseEntity<>(message, HttpStatus.OK);
         }catch (PostNotFound p){
             return new ResponseEntity<>(p.getMessage(),HttpStatus.NOT_FOUND);
         }
    }

    @GetMapping("/get_all_comments_post")
    public ResponseEntity getPostComments(@RequestParam("post_id") long post_id) throws PostNotFound{
        try{
            List<CommentsResponseDto> commentList=commentService.getAllCommentsOnPost(post_id);
              return new ResponseEntity<>(commentList, HttpStatus.OK);
        }catch (PostNotFound p){
            return new ResponseEntity<>(p.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
