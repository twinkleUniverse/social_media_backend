package com.apiwiz.SocialMedia.dto.ResponseDTO.user_response_dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProfileResponseDto {
    String username;
    String profile_picture;
    int number_of_post;
    int number_of_followers;
    int number_of_followings;
    String bio;

}
