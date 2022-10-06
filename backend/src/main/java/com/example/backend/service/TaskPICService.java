package com.example.backend.service;

import com.example.backend.entity.Task;
import com.example.backend.entity.TaskPIC;
import com.example.backend.entity.User;
import com.example.backend.repository.TaskPICRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskPICService {
    @Autowired
    private TaskPICRepository picRepository;
    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;

    public List<User> getPICsByTask(Long no) {
        List<TaskPIC> pics = picRepository.getAllByTask(taskService.getTaskByNo(no));
        List<User> users = new ArrayList<>();

        for (TaskPIC pic : pics) users.add(pic.getUser());
        return users;
    }

    public List<Task> getTasksByUser(Long no) {
        List<TaskPIC> pics = picRepository.getAllByUser(userService.getUserByNo(no));
        List<Task> tasks = new ArrayList<>();

        for (TaskPIC pic : pics) tasks.add(pic.getTask());
        return tasks;
    }

    public boolean addTaskPIC(Long userNo, Long taskNo) {
        User user = userService.getUserByNo(userNo);
        Task task = taskService.getTaskByNo(taskNo);
        if (user == null || task == null) return false;

        TaskPIC pic = new TaskPIC(null, user, task);
        picRepository.save(pic);
        return true;
    }

    public boolean deleteTaskPIC(Long userNo, Long taskNo) {
        User user = userService.getUserByNo(userNo);
        Task task = taskService.getTaskByNo(taskNo);
        if (user == null || task == null) return false;

        TaskPIC pic = picRepository.findByUserAndTask(user, task);
        if (pic == null) return false;

        picRepository.delete(pic);
        return true;
    }
}
