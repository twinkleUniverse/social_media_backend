package com.apiwiz.SocialMedia.dto.ResponseDTO.comment_response_dto;

import com.apiwiz.SocialMedia.model.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Date;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentsResponseDto {
    String content;
    Date commentDate;
    String commentUserName;
}
