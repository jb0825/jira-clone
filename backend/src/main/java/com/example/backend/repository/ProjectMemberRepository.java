package com.example.backend.repository;

import com.example.backend.entity.Project;
import com.example.backend.entity.ProjectMember;
import com.example.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember, Long> {
    ProjectMember findByNo(Long no);
    ProjectMember findByUserAndProject(User user, Project project);
    List<ProjectMember> findAllByUser(User user);
    List<ProjectMember> findAllByProject(Project project);

    @Query("SELECT pm FROM ProjectMember pm GROUP BY pm.user, pm.project HAVING pm.user = ?1")
    List<ProjectMember> getAllByUser(User user);
    @Query("SELECT pm FROM ProjectMember pm GROUP BY pm.project, pm.user HAVING pm.project = ?1")
    List<ProjectMember> getAllByProject(Project project);
}
