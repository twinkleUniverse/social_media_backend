package com.apiwiz.SocialMedia.transformer;

import com.apiwiz.SocialMedia.dto.RequestDTO.user_request_dto.UserRequestDto;
import com.apiwiz.SocialMedia.dto.ResponseDTO.user_response_dto.ProfileResponseDto;
import com.apiwiz.SocialMedia.dto.ResponseDTO.user_response_dto.UserResponseDto;
import com.apiwiz.SocialMedia.model.User;

public class UserTransformer {

    public static User userRequestToUser(UserRequestDto userRequestDto){
        return User.builder()
                .username(userRequestDto.getUsername())
                .email(userRequestDto.getEmailId())
                .password(userRequestDto.getPassword())
                .bio(userRequestDto.getBio())
                .profilePicture(userRequestDto.getProfile())
                .build();
    }

    public static UserResponseDto userToUserResponseDto(User user,String msg){
        return UserResponseDto.builder()
                .username(user.getUsername())
                .emailId(user.getEmail())
                .bio(user.getBio())
                .message(msg)
                .build();

    }

    public static ProfileResponseDto profileToUserResponseDto(User user) {
        return ProfileResponseDto.builder()
                .username(user.getUsername())
                .profile_picture(user.getProfilePicture())
                .number_of_followings(user.getFollowing().size())
                .number_of_followers(user.getFollowers().size())
                .bio(user.getBio())
                .build();
    }
}
