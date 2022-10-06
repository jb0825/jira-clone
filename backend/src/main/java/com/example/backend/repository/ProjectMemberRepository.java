package com.example.backend.repository;

import com.example.backend.entity.Project;
import com.example.backend.entity.ProjectMember;
import com.example.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember, Long> {
    ProjectMember findByNo(Long no);
    List<ProjectMember> findAllByUser(User user);
    List<ProjectMember> findAllByProject(Project project);
}
