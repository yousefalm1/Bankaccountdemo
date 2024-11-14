package com.example.demo.service;

import com.example.demo.bo.UserProfileResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import com.example.demo.bo.CreateUserRequest;
import com.example.demo.bo.UpdateUserProfileRequest;
import com.example.demo.bo.UserResponse;
import com.example.demo.entity.AccountEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository userRepository, AccountRepository accountRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserResponse updateUserProfile(UpdateUserProfileRequest request) {
        // Retrieve the current authenticated user's username
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // Find the user by username
        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Update fields if they are provided
        if (request.getUsername() != null) {
            userEntity.setUsername(request.getUsername());
        }
        if (request.getEmail() != null) {
            userEntity.setUsername(request.getEmail());
        }
        if (request.getPhoneNumber() != null) {
            userEntity.setPhoneNumber(request.getPhoneNumber());
        }
        if (request.getAddress() != null) {
            userEntity.setAddress(request.getAddress());
        }
        if (request.getPassword() != null) {
            userEntity.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        }

        userEntity = userRepository.save(userEntity);
        return new UserResponse(userEntity.getId(), userEntity.getUsername());
    }


    @Override
    public UserResponse createUser(CreateUserRequest request) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(request.getEmail());
        userEntity.setPhoneNumber(request.getPhoneNumber());
        userEntity.setAddress(request.getAddress());
        userEntity.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        userEntity = userRepository.save(userEntity);

        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setUser(userEntity);
        accountEntity.setBalance(0.0);
        accountEntity = accountRepository.save(accountEntity);

        UserResponse response = new UserResponse(userEntity.getId(), userEntity.getUsername());
        return response;
    }

    @Override
    public UserProfileResponse getUserProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return new UserProfileResponse(userEntity.getId(), userEntity.getUsername(), userEntity.getPhoneNumber(), userEntity.getAddress());

    }

    @Override
    public List<User> getAllUsers() {
        return List.of();
    }

    @Override
    public User getUserById(Long id) {
        return null;
    }

    @Override
    public void deleteUserById(Long id) {

    }

}