package com.example.backend.service;

import com.example.backend.dto.UserDto;
import com.example.backend.entity.User;
import com.example.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User getUserByNo(Long no) { return userRepository.findByNo(no); }

    public User getUserByEmail(String email) { return userRepository.findByEmail(email); }

    public List<UserDto> getAllUsers() { return userRepository.selectAll(); }

    public User signIn(String email, String password) {
        User user = userRepository.findByEmail(email);

        if (user == null) return null;
        if (!passwordEncoder.matches(password, user.getPassword())) return null;

        return user;
    }

    public boolean signUp(User user) {
        if (user == null) return false;
        if (user.getName() == null || user.getPassword() == null) return false;

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }
}
