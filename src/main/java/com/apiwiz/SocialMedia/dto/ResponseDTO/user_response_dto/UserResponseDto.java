package com.apiwiz.SocialMedia.dto.ResponseDTO.user_response_dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponseDto {
    String username;
    String emailId;
    String bio;
    String message;
}
