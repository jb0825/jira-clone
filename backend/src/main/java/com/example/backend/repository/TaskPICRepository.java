package com.example.backend.repository;

import com.example.backend.entity.Task;
import com.example.backend.entity.TaskPIC;
import com.example.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskPICRepository extends JpaRepository<TaskPIC, Long> {
    TaskPIC findByNo(Long no);
    List<TaskPIC> findAllByUser(User user);
    List<TaskPIC> findAllByTask(Task task);
}
