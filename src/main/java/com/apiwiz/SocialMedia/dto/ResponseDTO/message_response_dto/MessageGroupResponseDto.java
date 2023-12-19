package com.apiwiz.SocialMedia.dto.ResponseDTO.message_response_dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MessageGroupResponseDto {
    String message;
    Date sent_date;
    String sendername;
    String group_name;
}
