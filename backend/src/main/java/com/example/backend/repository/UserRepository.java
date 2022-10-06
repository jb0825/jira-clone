package com.example.backend.repository;

import com.example.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByNo(Long no);
    User findByEmailAndPassword(String email, String password);
    User findByEmail(String email);
}
