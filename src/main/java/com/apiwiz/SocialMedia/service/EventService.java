package com.apiwiz.SocialMedia.service;

import com.apiwiz.SocialMedia.dto.ResponseDTO.post_response_dto.PostResponseDto;
import com.apiwiz.SocialMedia.exception.postExceptions.PostNotFound;
import com.apiwiz.SocialMedia.exception.userExceptions.UserNotExist;

public interface EventService {
    public PostResponseDto likeThePost(long post_id, String likedUsername) throws PostNotFound;
    public String sharePost(long post_id,String sendername,String recievername) throws UserNotExist,PostNotFound;
    public String dislike(long postId,String likedUserName) throws PostNotFound;
    public int getPostlikes(long post_id) throws PostNotFound;
}
