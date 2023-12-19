package com.apiwiz.SocialMedia.dto.ResponseDTO.message_response_dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MessageResponseDto {
    String message;
    Date sent_date;
    String sender_name;
    String receiver_name;
}
