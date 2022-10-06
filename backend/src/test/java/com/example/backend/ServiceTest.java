package com.example.backend;

import com.example.backend.entity.Board;
import com.example.backend.entity.Project;
import com.example.backend.entity.Task;
import com.example.backend.entity.User;
import com.example.backend.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ServiceTest {
    /* USER SERVICE TEST */
    @Autowired
    private UserService userService;

    @Test
    public void signUpTest() {
        User user = new User();
        user.setName("name");
        user.setPassword("password");
        user.setEmail("test@example.com");
        userService.signUp(user);
    }
    @Test
    public void signInTest() {
        String email = "test@example.com";
        String password = "password";

        System.out.println(userService.signIn(email, password));
        password = "xxx";
        System.out.println(userService.signIn(email, password));
    }
    @Test
    public void updateUserTest() {
        User user = userService.getUserByNo(8L);
        user.setCompany("company");
        user.setDepartment("department");
        userService.signUp(user);
    }

    /* PROJECT SERVICE TEST */
    @Autowired
    private ProjectService projectService;

    @Test
    public void createProjectTest() {
        Project project = new Project();
        project.setName("project 2");
        project.setLeader(userService.getUserByNo(8L));
        projectService.createProject(project);
    }
    @Test
    public void getAllProjectTest() {
        for (Project p : projectService.getAllProjects()) System.out.println(p);
    }
    @Test
    public void getProjectsByLeaderTest() {
        System.out.println(projectService.getProjectsByLeaderNo(8L));
    }
    @Test
    public void deleteProjectTest() {
        System.out.println(projectService.deleteProject(12L));
    }

    /* PROJECT MEMBER SERVICE TEST */
    @Autowired
    private ProjectMemberService memberService;

    @Test
    public void createMemberTest() {
        System.out.println(memberService.addProjectMember(8L, 11L)); // true
        System.out.println(memberService.addProjectMember(1L, 11L)); // false
    }
    @Test
    public void getMemberByProjectTest() {
        for (User u : memberService.getProjectMembersByProject(11L)) System.out.println(u);
    }
    @Test
    public void getProjectsByUser() {
        for (Project p : memberService.getProjectsByUser(8L)) System.out.println(p);
    }

    /* BOARD SERVICE TEST */
    @Autowired
    private BoardService boardService;

    @Test
    public void createBoardTest() {
        Board board = new Board();
        board.setName("board");
        board.setProject(projectService.getProjectByNo(11L));
        System.out.println(boardService.createBoard(board));
    }
    @Test
    public void getBoardByProjectTest() {
        for (Board b : boardService.getBoardsByProjectNo(11L)) System.out.println(b);
    }
    @Test
    public void deleteBoardTest() {
        System.out.println(boardService.deleteBoard(6L));
    }

    /* TASK SERVICE TEST */
    @Autowired
    private TaskService taskService;

    @Test
    public void createTaskTest() {
        Task task = new Task();
        task.setTitle("task");
        task.setBoard(boardService.getBoardByNo(5L));
        System.out.println(taskService.createTask(task));
    }
    @Test
    public void getTasksByBoardNoTest() {
        for(Task t : taskService.getTasksByBoardNo(5L)) System.out.println(t);
    }
    @Test
    public void deleteTaskTest() {
        System.out.println(taskService.deleteTask(12L));
    }

    /* TASK PERSON IN CHARGE SERVICE TEST */
    @Autowired
    private TaskPICService picService;

    @Test
    public void createPicTest() {
        System.out.println(picService.addTaskPIC(8L, 10L));
        System.out.println(picService.addTaskPIC(8L, 11L));
    }
    @Test
    public void getPicByTaskTest() {
        for (User u : picService.getPICsByTask(10L)) System.out.println(u);
    }
    @Test
    public void getTaskByUserTest() {
        for (Task t : picService.getTasksByUser(8L)) System.out.println(t);
    }
}
