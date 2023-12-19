package com.apiwiz.SocialMedia.service;

import com.apiwiz.SocialMedia.dto.RequestDTO.post_request_dto.PostRequestDto;
import com.apiwiz.SocialMedia.dto.RequestDTO.post_request_dto.RepostRequestDto;
import com.apiwiz.SocialMedia.dto.ResponseDTO.post_response_dto.PostResponseDto;
import com.apiwiz.SocialMedia.exception.postExceptions.PostNotFound;
import com.apiwiz.SocialMedia.exception.userExceptions.UserNotExist;

import java.util.List;

public interface PostService {
    public PostResponseDto post_content(PostRequestDto postRequestDto);
    public List<PostResponseDto> allPostList(String username) throws UserNotExist;

    public String deletePost(long postId) throws PostNotFound;
    public PostResponseDto rePost(RepostRequestDto repostRequestDto) throws UserNotExist,PostNotFound;
    }
