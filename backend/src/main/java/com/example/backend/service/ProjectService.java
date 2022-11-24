package com.example.backend.service;

import com.example.backend.entity.Project;
import com.example.backend.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private UserService userService;

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public List<Project> getProjectsByLeaderNo(Long no) {
        return projectRepository.findAllByLeader(userService.getUserByNo(no));
    }

    public Project getProjectByNo(Long no) { return projectRepository.findByNo(no); }

    public boolean createProject(Project project) {
        if (project == null) return false;
        if (project.getName() == null || project.getLeader() == null) return false;

        projectRepository.save(project);
        return true;
    }

    public boolean deleteProject(Long projectNo, Long leaderNo) {
        Project project = projectRepository.findByNo(projectNo);

        if (project == null) return false;
        if (checkLeader(project, leaderNo)) return false;

        projectRepository.delete(project);
        return true;
    }

    /**
     * 로그인한 사용자와 Project leader 가 일치하는지 체크
     */
    public boolean checkLeader(Project project, Long leaderNo) {
        if (project == null) return false;
        return Objects.equals(project.getLeader().getNo(), leaderNo);
    }
}
