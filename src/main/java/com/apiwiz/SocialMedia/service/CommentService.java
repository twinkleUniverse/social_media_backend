package com.apiwiz.SocialMedia.service;

import com.apiwiz.SocialMedia.dto.RequestDTO.comment_request_dto.CommentRequestDto;
import com.apiwiz.SocialMedia.dto.ResponseDTO.comment_response_dto.CommentsResponseDto;
import com.apiwiz.SocialMedia.dto.ResponseDTO.post_response_dto.PostResponseDto;
import com.apiwiz.SocialMedia.exception.postExceptions.PostNotFound;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface CommentService {

    public PostResponseDto commentOnPost(CommentRequestDto commentRequestDto) throws PostNotFound;
    public String deleteComment(long comment_id,long post_id) throws PostNotFound;
    public List<CommentsResponseDto> getAllCommentsOnPost(@RequestParam("post_id") long post_id) throws PostNotFound;
}
