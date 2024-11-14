package com.example.demo.service;

import com.example.demo.entity.UserEntity;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    @Autowired
    private UserRepository userRepository;


    public void performTransaction(Long fromUserId, Long toUserId, Double amount, String transactionType) {
        UserEntity fromUser = userRepository.findById(fromUserId).orElseThrow(() -> new UserNotFoundException("User with ID " + fromUserId + " not found"));
        UserEntity toUser = userRepository.findById(toUserId).orElseThrow(() -> new UserNotFoundException("User with ID " + toUserId + " not found"));


    }
}
