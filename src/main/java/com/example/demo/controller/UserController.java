package com.example.demo.controller;

import com.example.demo.bo.UpdateUserProfileRequest;
import com.example.demo.bo.UserProfileResponse;
import com.example.demo.bo.UserResponse;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/profile")
    public UserProfileResponse getProfile() {
        return userService.getUserProfile();

    }

    @PutMapping("/update-profile")
    public UserResponse updateProfile(@RequestBody UpdateUserProfileRequest request) {
        // Calls the service to update the profile using the current authenticated user’s details
        return userService.updateUserProfile(request);
    }
}
