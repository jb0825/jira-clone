package com.example.backend.service;

import com.example.backend.entity.Project;
import com.example.backend.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public boolean deleteProject(Long no) {
        Project project = projectRepository.findByNo(no);
        if (project == null) return false;

        projectRepository.delete(project);
        return true;
    }
}
