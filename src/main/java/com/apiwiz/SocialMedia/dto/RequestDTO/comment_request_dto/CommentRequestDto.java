package com.apiwiz.SocialMedia.dto.RequestDTO.comment_request_dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentRequestDto {
    String content;
    String creator;
    long post_id;

}
