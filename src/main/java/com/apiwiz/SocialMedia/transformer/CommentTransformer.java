package com.apiwiz.SocialMedia.transformer;

import com.apiwiz.SocialMedia.dto.RequestDTO.comment_request_dto.CommentRequestDto;
import com.apiwiz.SocialMedia.dto.ResponseDTO.comment_response_dto.CommentsResponseDto;
import com.apiwiz.SocialMedia.model.Comment;
import com.apiwiz.SocialMedia.model.Post;
import com.apiwiz.SocialMedia.model.User;

public class CommentTransformer {
    public static Comment commentRequestdtoTocomment(CommentRequestDto commentRequestDto, User user, Post post){
        return Comment.builder()
                .content(commentRequestDto.getContent())
                .user(user)
                .post(post)
                .build();

    }

    public  static CommentsResponseDto commentToCommentReseDto(Comment comment){
        return CommentsResponseDto.builder()
                .content(comment.getContent())
                .commentUserName(comment.getUser().getUsername())
                .commentDate(comment.getComment_date())
                .build();
    }
}
