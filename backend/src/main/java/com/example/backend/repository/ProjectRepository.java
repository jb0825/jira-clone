package com.example.backend.repository;

import com.example.backend.entity.Project;
import com.example.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Project findByNo(Long no);
    List<Project> findAllByLeader(User leader);
}
