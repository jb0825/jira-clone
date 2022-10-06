package com.example.backend.repository;

import com.example.backend.entity.Board;
import com.example.backend.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Task findByNo(Long no);
    Task findFirstByOrderByNoDesc();
    List<Task> findAllByBoard(Board board);
    List<Task> findAllByPriority(int priority);

}
