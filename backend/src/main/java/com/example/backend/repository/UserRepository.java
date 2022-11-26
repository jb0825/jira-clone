package com.example.backend.repository;

import com.example.backend.dto.UserDto;
import com.example.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByNo(Long no);
    User findByEmailAndPassword(String email, String password);
    User findByEmail(String email);

    @Query("SELECT new com.example.backend.dto.UserDto(u.no, u.email, u.name, u.position, u.department, u.company) FROM User u")
    List<UserDto> selectAll();

    @Query("SELECT new com.example.backend.dto.UserDto(u.no, u.email, u.name, u.position, u.department, u.company) FROM User u WHERE u.email = ?1")
    UserDto selectByEmail(String email);
}
