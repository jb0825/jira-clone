package com.example.backend.repository;

import com.example.backend.entity.Task;
import com.example.backend.entity.TaskPIC;
import com.example.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskPICRepository extends JpaRepository<TaskPIC, Long> {
    TaskPIC findByNo(Long no);
    TaskPIC findByUserAndTask(User user, Task task);
    List<TaskPIC> findAllByUser(User user);
    List<TaskPIC> findAllByTask(Task task);

    @Query("SELECT pic FROM TaskPIC pic GROUP BY pic.user, pic.task HAVING pic.user = ?1")
    List<TaskPIC> getAllByUser(User user);
    @Query("SELECT pic FROM TaskPIC pic GROUP BY pic.task, pic.user HAVING pic.task = ?1")
    List<TaskPIC> getAllByTask(Task task);
}
