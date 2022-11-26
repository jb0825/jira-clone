package com.example.backend.service;

import com.example.backend.entity.Board;
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

    public boolean createTask(Task task, Long boardNo) {
        if (task == null) return false;

        Board board = boardService.getBoardByNo(boardNo);
        if (board == null) return false;

        Task last = taskRepository.getLastByProject(task.getBoard().getProject());
        long lastNo = last != null ? last.getNo() : 1L;

        task.setTaskKey(task.getBoard().getProject().getName() + "-" + lastNo);
        task.setBoard(board);
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
