package com.example.demo.service;

import com.example.demo.entity.UserEntity;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl {
    public static class UserServiceGetUsers {

        @Autowired
        private UserRepository userRepository;

        public List<UserEntity> getAllUsers() {
            return userRepository.findAll();
        }


        public static class UserService2GetById {

            private UserRepository userRepository;

            public UserEntity getUserById(Long id) {
                return userRepository.findById(id)
                        .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));
            }
        }

        public void deleteUserById(Long id) {
            if (userRepository.existsById(id)) {
                userRepository.deleteById(id);
            } else {
                throw new UserNotFoundException("User with ID " + id + " not found");
            }
        }
    }
}