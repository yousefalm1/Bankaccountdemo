package com.example.demo.service;

import com.example.demo.bo.CreateUserRequest;
import com.example.demo.bo.UserResponse;
import com.example.demo.entity.AccountEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
}