package com.example.backend.service;

import com.example.backend.entity.Project;
import com.example.backend.entity.ProjectMember;
import com.example.backend.entity.User;
import com.example.backend.repository.ProjectMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectMemberService {

    @Autowired
    private ProjectMemberRepository projectMemberRepository;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private UserService userService;

    public List<User> getProjectMembersByProject(Long no) {
        Project project = projectService.getProjectByNo(no);
        if (project == null) return null;

        List<ProjectMember> projectMembers = projectMemberRepository.getAllByProject(project);
        List<User> users = new ArrayList<>();

        for (ProjectMember pm : projectMembers) users.add(pm.getUser());
        return users;
    }

    public List<Project> getProjectsByUser(Long no) {
        User user = userService.getUserByNo(no);
        if (user == null) return null;

        List<ProjectMember> projectMembers = projectMemberRepository.getAllByUser(user);
        List<Project> projects = new ArrayList<>();

        for (ProjectMember pm : projectMembers) projects.add(pm.getProject());
        return projects;
    }

    public boolean addProjectMember(Long userNo, Long projectNo, Long leaderNo) {
        Project project = projectService.getProjectByNo(projectNo);
        User user = userService.getUserByNo(userNo);

        if (!projectService.checkLeader(project, leaderNo) || user == null) return false;

        ProjectMember member = new ProjectMember(null, user, project);
        projectMemberRepository.save(member);
        return true;
    }

    public boolean deleteProjectMember(Long userNo, Long projectNo, Long leaderNo) {
        Project project = projectService.getProjectByNo(projectNo);
        User user = userService.getUserByNo(userNo);

        if (!projectService.checkLeader(project, leaderNo) || user == null) return false;

        ProjectMember member = projectMemberRepository.findByUserAndProject(user, project);
        if (member == null) return false;

        projectMemberRepository.delete(member);
        return true;
    }

}
