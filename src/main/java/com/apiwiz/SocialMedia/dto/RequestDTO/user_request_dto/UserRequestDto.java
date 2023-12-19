package com.apiwiz.SocialMedia.dto.RequestDTO.user_request_dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequestDto {
     String username;
     String emailId;
     String password;
     String bio;
     String profile;
}
