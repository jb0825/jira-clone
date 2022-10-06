package com.example.backend.service;

import com.example.backend.entity.Task;
import com.example.backend.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private BoardService boardService;

    public List<Task> getTasksByBoardNo(Long no) {
        return taskRepository.findAllByBoard(boardService.getBoardByNo(no));
    }

    public Task getTaskByNo(Long no) { return taskRepository.findByNo(no); }

    public boolean createTask(Task task) {
        if (task == null) return false;

        Task last = taskRepository.getLastByProject(task.getBoard().getProject());
        long lastNo = last != null ? last.getNo() : 1L;

        task.setTaskKey(task.getBoard().getProject().getName() + "-" + lastNo);
        taskRepository.save(task);
        return true;
    }

    public boolean deleteTask(Long no) {
        Task task = taskRepository.findByNo(no);
        if (task == null) return false;

        taskRepository.delete(task);
        return true;
    }
}
