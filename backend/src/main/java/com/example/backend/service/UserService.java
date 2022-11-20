package com.example.backend.service;

import com.example.backend.entity.User;
import com.example.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User getUserByNo(Long no) { return userRepository.findByNo(no); }

    public User getUserByEmail(String email) { return userRepository.findByEmail(email); }

    public boolean signIn(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user == null) return false;

        return passwordEncoder.matches(password, user.getPassword());
    }

    public boolean signUp(User user) {
        if (user == null) return false;
        if (user.getName() == null || user.getPassword() == null) return false;

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }
}
