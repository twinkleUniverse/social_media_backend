package com.apiwiz.SocialMedia.service.impl;

import com.apiwiz.SocialMedia.dto.RequestDTO.user_request_dto.UserRequestDto;
import com.apiwiz.SocialMedia.dto.ResponseDTO.user_response_dto.ProfileResponseDto;
import com.apiwiz.SocialMedia.dto.ResponseDTO.user_response_dto.UserResponseDto;
import com.apiwiz.SocialMedia.exception.userExceptions.UserAlreadyExist;
import com.apiwiz.SocialMedia.exception.userExceptions.UserNotExist;
import com.apiwiz.SocialMedia.model.User;
import com.apiwiz.SocialMedia.repository.UserRepository;
import com.apiwiz.SocialMedia.service.UserService;
import com.apiwiz.SocialMedia.transformer.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    public UserResponseDto registerUser(UserRequestDto userRequestDto) throws UserAlreadyExist {
        User user = UserTransformer.userRequestToUser(userRequestDto);
        Optional<User>optionalUser=userRepository.findById(user.getUser_id());
        if(optionalUser.isPresent()){
            throw new UserAlreadyExist("Already Registered ! Please login");
        }
         User savedUser=userRepository.save(user);
        //Email verification pending.
        return UserTransformer.userToUserResponseDto(savedUser,"Registered Successfully");
    }

    public UserResponseDto updateUser(String username,String profileUrl) throws UserNotExist {
        Optional<User>optionalUser=userRepository.findByUsername(username);
        if(!optionalUser.isPresent()){
            throw new UserNotExist("User not exist");
        }
        User user=optionalUser.get();
        user.setProfilePicture(profileUrl);
        User savedUser=userRepository.save(user);
        return UserTransformer.userToUserResponseDto(savedUser,"Profile picture updated successfully");
    }


    public UserResponseDto updateUsername(String username) throws UserNotExist {
        Optional<User>optionalUser=userRepository.findByUsername(username);
        if(!optionalUser.isPresent()){
            throw new UserNotExist("User not exist");
        }
        User user=optionalUser.get();
        user.setUsername(username);
        User savedUser=userRepository.save(user);
        return UserTransformer.userToUserResponseDto(savedUser,"User Name updated successfully");
    }

    public UserResponseDto updateUserBio(String username,String bio) throws UserNotExist {
        Optional<User>optionalUser=userRepository.findByUsername(username);
        if(!optionalUser.isPresent()){
            throw new UserNotExist("User not exist");
        }
        User user=optionalUser.get();
        user.setBio(bio);
        User savedUser=userRepository.save(user);
        return UserTransformer.userToUserResponseDto(savedUser,"Bio updated successfully");
    }

    public UserResponseDto updatePass(String username,String newPassword) throws UserNotExist {
        Optional<User>optionalUser=userRepository.findByUsername(username);
        if(!optionalUser.isPresent()){
            throw new UserNotExist("User not exist");
        }
        User user=optionalUser.get();
        user.setPassword(newPassword);
        User savedUser=userRepository.save(user);
        return UserTransformer.userToUserResponseDto(savedUser,"Password updated successfully");
    }

    public ProfileResponseDto getUser(String username) throws  UserNotExist{
        Optional<User>optionalUser=userRepository.findByUsername(username);
        if(!optionalUser.isPresent()) {
            throw new UserNotExist("User not exist");
        }
       return UserTransformer.profileToUserResponseDto(optionalUser.get());
    }
}
