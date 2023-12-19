package com.apiwiz.SocialMedia.dto.RequestDTO.message_request_dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MessageGroupRequestDto {
    String content;
    String senderName;
    long group_id;
}
