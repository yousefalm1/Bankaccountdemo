package com.example.demo.service;

import com.example.demo.bo.CreateUserRequest;
import com.example.demo.bo.UpdateUserProfileRequest;
import com.example.demo.bo.UserProfileResponse;
import com.example.demo.bo.UserResponse;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public interface UserService {
    UserResponse createUser(CreateUserRequest request);

    UserResponse updateUserProfile(UpdateUserProfileRequest request);

    UserProfileResponse getUserProfile();

    List<User> getAllUsers();

    User getUserById(Long id);

    void deleteUserById(Long id);

}
