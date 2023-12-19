package com.apiwiz.SocialMedia.dto.RequestDTO.message_request_dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MessageRequestDto {
    String content;
    String receiverName;
    String senderName;
}
