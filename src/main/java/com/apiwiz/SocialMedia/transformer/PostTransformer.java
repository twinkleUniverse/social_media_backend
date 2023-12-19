package com.apiwiz.SocialMedia.transformer;

import com.apiwiz.SocialMedia.Enum.EventType;
import com.apiwiz.SocialMedia.dto.RequestDTO.post_request_dto.PostRequestDto;
import com.apiwiz.SocialMedia.dto.RequestDTO.post_request_dto.RepostRequestDto;
import com.apiwiz.SocialMedia.dto.ResponseDTO.post_response_dto.PostResponseDto;
import com.apiwiz.SocialMedia.model.Event;
import com.apiwiz.SocialMedia.model.Post;
import com.apiwiz.SocialMedia.model.User;

import java.util.ArrayList;
import java.util.List;

public class PostTransformer {

    public static Post postRequestDtoToPost(PostRequestDto postRequestDto){
        return Post.builder()
                .content(postRequestDto.getContent())
                .caption(postRequestDto.getCaption())
                .privacy_status(postRequestDto.getPrivacySetting())
                .build();
    }

    public static PostResponseDto postToPostResponseDto(Post post){
        List<Event>eventList=new ArrayList<>();
        int numberoflikes=0;
        for(Event event:eventList){
            if(event.getEventType()== EventType.LIKE){
                numberoflikes++;
            }
        }
        return PostResponseDto.builder()
                .username(post.getUser().getUsername())
                .content(post.getContent())
                .caption(post.getCaption())
                .post_date(post.getPost_date())
                .commentList(post.getPost_comments_List())
                .number_of_likes(numberoflikes)
                .build();
    }

    public static Post repostRequestDtoToPost(RepostRequestDto repostRequestDto,User user){
        return Post.builder()
                .post_id(repostRequestDto.getPost_id())
                .user(user)
                .caption(repostRequestDto.getCaption())
                .privacy_status(repostRequestDto.getStatus())
                .build();
    }
}
