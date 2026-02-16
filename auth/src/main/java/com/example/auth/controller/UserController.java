package com.example.auth.controller;

import com.example.auth.model.User;
import com.example.auth.security.JwtUtil;
import com.example.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public String register(@RequestBody User user) {

        User savedUser = userService.register(user);

        return jwtUtil.generateToken(savedUser.getUsername());
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {

        User dbUser = userService.findByUsername(user.getUsername());

        if (!dbUser.getPassword().equals(user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return jwtUtil.generateToken(user.getUsername());
    }
}

