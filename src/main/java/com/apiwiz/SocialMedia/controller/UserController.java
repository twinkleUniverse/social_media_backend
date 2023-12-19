package com.apiwiz.SocialMedia.controller;

import com.apiwiz.SocialMedia.dto.RequestDTO.user_request_dto.UserRequestDto;
import com.apiwiz.SocialMedia.dto.ResponseDTO.user_response_dto.ProfileResponseDto;
import com.apiwiz.SocialMedia.dto.ResponseDTO.user_response_dto.UserResponseDto;
import com.apiwiz.SocialMedia.exception.userExceptions.UserAlreadyExist;
import com.apiwiz.SocialMedia.exception.userExceptions.UserNotExist;
import com.apiwiz.SocialMedia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/User")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/Register")
    public ResponseEntity register(@RequestBody UserRequestDto userRequestDto) throws UserAlreadyExist{
        try {
            UserResponseDto newUser = userService.registerUser(userRequestDto);
            return new ResponseEntity(newUser,HttpStatus.CREATED);
        }
        catch (UserAlreadyExist e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.ALREADY_REPORTED);
        }
    }

    @PutMapping("/update_profile")
    public ResponseEntity updateProfile(@RequestParam("username") String username,@RequestParam("profile_url") String profile) throws UserNotExist {
        try{
            UserResponseDto newUser=userService.updateUser(username,profile);
            return new ResponseEntity(newUser,HttpStatus.OK);
        }catch (UserNotExist e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update_name")
    public ResponseEntity updateUsername(@RequestParam("username") String username) throws UserNotExist {
        try{
            UserResponseDto newUser=userService.updateUsername(username);
            return new ResponseEntity(newUser,HttpStatus.OK);
        }catch (UserNotExist e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update_bio")
    public ResponseEntity updateBio(@RequestParam("username") String username,@RequestParam("Bio") String bio) throws UserNotExist {
        try{
            UserResponseDto newUser=userService.updateUserBio(username,bio);
            return new ResponseEntity(newUser,HttpStatus.OK);
        }catch (UserNotExist e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update_password")
    public ResponseEntity updatepassword(@RequestParam("username") String username,@RequestParam("password") String newPassword) throws UserNotExist {
        try{
            UserResponseDto newUser=userService.updatePass(username,newPassword);
            return new ResponseEntity(newUser,HttpStatus.OK);
        }catch (UserNotExist e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get_User")
    public ResponseEntity getUserProfile(@RequestParam("username") String username) throws UserNotExist{
        try{
            ProfileResponseDto newUser=userService.getUser(username);
            return new ResponseEntity(newUser,HttpStatus.OK);
        }catch (UserNotExist e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
