package com.example.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.dto.LoginRequest;
import com.example.backend.dto.RegisterRequest;
import com.example.backend.entity.User;
import com.example.backend.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // REGISTER
    public String register(RegisterRequest request) {

        User existingUser =
                userRepository.findByEmail(request.getEmail());

        if(existingUser != null) {
            return "Email already exists";
        }

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(request.getRole());

        userRepository.save(user);

        return "Registration Successful";
    }

    public String login(LoginRequest request) {

    User user =
            userRepository.findByEmail(request.getEmail());

    // USER NOT FOUND
    if(user == null) {
        return "User Not Found";
    }

    // PASSWORD CHECK
    if(user.getPassword().equals(request.getPassword())) {

        return user.getRole();
    }

    return "Invalid Password";
}
}