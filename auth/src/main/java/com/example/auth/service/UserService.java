package com.example.auth.service;

import com.example.auth.repository.UserRepository;
import com.example.auth.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
    public class UserService {

        @Autowired
        private UserRepository userRepository;

        public User register(User user) {
            return userRepository.save(user);
        }

        public User findByUsername(String username) {
            return userRepository.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("User not found"));
        }
    }
