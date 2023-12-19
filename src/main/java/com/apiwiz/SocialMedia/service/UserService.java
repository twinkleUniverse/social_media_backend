package com.apiwiz.SocialMedia.service;

import com.apiwiz.SocialMedia.dto.RequestDTO.user_request_dto.UserRequestDto;
import com.apiwiz.SocialMedia.dto.ResponseDTO.user_response_dto.ProfileResponseDto;
import com.apiwiz.SocialMedia.dto.ResponseDTO.user_response_dto.UserResponseDto;
import com.apiwiz.SocialMedia.exception.userExceptions.UserAlreadyExist;
import com.apiwiz.SocialMedia.exception.userExceptions.UserNotExist;


public interface UserService {
    public  UserResponseDto registerUser(UserRequestDto userRequestDto) throws UserAlreadyExist;

    public UserResponseDto updateUser(String username, String profile) throws UserNotExist;

    public UserResponseDto updateUsername(String username) throws UserNotExist;
    public UserResponseDto updateUserBio(String username,String bio) throws UserNotExist;
    public UserResponseDto updatePass(String username,String newPassword) throws UserNotExist;

    public ProfileResponseDto getUser(String username) throws  UserNotExist;
}
