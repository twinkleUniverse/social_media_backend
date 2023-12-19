package com.apiwiz.SocialMedia.dto.ResponseDTO.post_response_dto;

import com.apiwiz.SocialMedia.model.Comment;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostResponseDto {
    String username;
    String content;
    String caption;
    Date post_date;
    List<Comment>commentList;
    int number_of_likes;

}
