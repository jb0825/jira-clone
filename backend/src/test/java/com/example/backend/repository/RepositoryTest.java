package com.example.backend.repository;

import com.example.backend.entity.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@SpringBootTest
public class RepositoryTest {

    /* USER REPOSITORY TEST */
    @Autowired
    private UserRepository userRepository;

    @Test
    public void userRepositoryCreateTest() {
        User user = new User();
        user.setName("test");
        user.setEmail("test@example.com");
        user.setPassword("password");
        user.setPosition("position");
        user.setDepartment("department");
        user.setCompany("company");
        userRepository.save(user);
    }
    @Test
    public void userRepositoryReadTest() {
        System.out.println(userRepository.findByNo(2L));
        System.out.println(userRepository.findByEmailAndPassword("test@example.com", "password"));
        System.out.println(userRepository.findByNo(1000L)); // 없을경우 null
    }
    @Test
    public void userRepositoryUpdateTest() {
        Long no = 2L;
        User user = userRepository.findByNo(no);
        user.setCompany("이직했어요");
        userRepository.save(user);
        System.out.println(userRepository.findByNo(no));
    }
    @Test
    public void userRepositoryDeleteTest() {
        userRepository.delete(userRepository.findByNo(3L));
    }

    /* PROJECT REPOSITORY TEST */
    @Autowired
    private ProjectRepository projectRepository;

    @Test
    public void projectRepositoryCreateTest() {
        User leader = userRepository.findByNo(2L);
        Project project = new Project();
        project.setName("project2");
        project.setLeader(leader);
        projectRepository.save(project);
    }
    @Transactional
    @Test
    public void projectRepositoryReadTest() {
        System.out.println(projectRepository.findByNo(1L));
    }
    @Transactional
    @Test
    public void projectRepositoryUpdateTest() {
        Long no = 1L;
        Project project = projectRepository.findByNo(no);
        project.setName("name changed");
        projectRepository.save(project);
        System.out.println(projectRepository.findByNo(no));
    }
    @Test
    public void projectRepositoryDeleteTest() {
        projectRepository.delete(projectRepository.findByNo(7L));
    }

    /* PROJECT MEMBER REPOSITORY TEST */
    @Autowired
    private ProjectMemberRepository projectMemberRepository;

    @Test
    public void projectMemberRepositoryCreateTest() {
        ProjectMember projectMember = new ProjectMember();
        projectMember.setUser(userRepository.findByNo(2L));
        projectMember.setProject(projectRepository.findByNo(1L));
        projectMemberRepository.save(projectMember);
    }
    @Transactional
    @Test
    public void projectMemberRepositoryReadTest() {
        System.out.println(projectMemberRepository.findByNo(1L));
        System.out.println(projectMemberRepository.findAllByUser(userRepository.findByNo(2L)));
        System.out.println(projectMemberRepository.findAllByProject(projectRepository.findByNo(1L)));
    }
    @Test
    public void projectMemberRepositoryUpdateTest() {
        ProjectMember projectMember = projectMemberRepository.findByNo(1L);
        projectMember.setProject(projectRepository.findByNo(8L));
        projectMemberRepository.save(projectMember);
    }
    @Test
    public void projectMemberRepositoryDeleteTest() {
        projectMemberRepository.delete(projectMemberRepository.findByNo(2L));
    }

    /* BOARD REPOSITORY TEST */
    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void boardRepositoryCreateTest() {
        Board board = new Board();
        board.setName("board");
        board.setProject(projectRepository.findByNo(1L));
        boardRepository.save(board);
    }
    @Transactional
    @Test
    public void boardRepositoryReadTest() {
        System.out.println(boardRepository.findByNo(1L).toString());
        System.out.println(boardRepository.findAllByProject(projectRepository.findByNo(1L)));
    }
    @Test
    public void boardRepositoryUpdateTest() {
        Board board = boardRepository.findByNo(1L);
        board.setName("name changed");
        boardRepository.save(board);
    }
    @Test
    public void boardRepositoryDeleteTest() {
        boardRepository.delete(boardRepository.findByNo(2L));
    }

    /* TASK REPOSITORY TEST */
    @Autowired
    private TaskRepository taskRepository;

    @Test
    public void taskRepositoryCreateTest() {
        Board board = boardRepository.findByNo(1L);
        Task task = new Task();
        task.setTitle("title");
        task.setDescription("description");
        task.setTaskKey(board.getProject().getName() + "-1");
        task.setBoard(board);
        taskRepository.save(task);
    }
    @Transactional
    @Test
    public void taskRepositoryReadTest() {
        System.out.println(taskRepository.findFirstByOrderByNoDesc());
        System.out.println(taskRepository.findByNo(1L));
        System.out.println(taskRepository.findAllByPriority(3));
        System.out.println(taskRepository.findAllByBoard(boardRepository.findByNo(1L)));
    }
    @Test
    public void taskRepositoryUpdateTest() {
        Task task = taskRepository.findByNo(1L);
        task.setDescription("description changed");
        task.setPriority(1);
        taskRepository.save(task);
    }
    @Test
    public void taskRepositoryDeleteTest() {
        taskRepository.delete(taskRepository.findByNo(7L));
    }

    /* TASK PIC REPOSITORY TEST */
    @Autowired
    private TaskPICRepository taskPICRepository;

    @Test
    public void taskPicRepositoryCreateTest() {
        TaskPIC pic = new TaskPIC();
        pic.setUser(userRepository.findByNo(2L));
        pic.setTask(taskRepository.findByNo(1L));
        taskPICRepository.save(pic);
    }
    @Test
    public void taskPicRepositoryReadTest() {
        System.out.println(taskPICRepository.findByNo(1L));
        System.out.println(taskPICRepository.findAllByUser(userRepository.findByNo(2L)));
        System.out.println(taskPICRepository.findAllByTask(taskRepository.findByNo(1L)));
    }
    @Test
    public void taskPicRepositoryUpdateTest() {
        TaskPIC pic = taskPICRepository.findByNo(1L);
        pic.setTask(taskRepository.findByNo(8L));
        taskPICRepository.save(pic);
    }
    @Test
    public void taskPicRepositoryDeleteTest() {
        taskPICRepository.delete(taskPICRepository.findByNo(2L));
    }
}
