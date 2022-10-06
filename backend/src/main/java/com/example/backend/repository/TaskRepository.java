package com.example.backend.repository;

import com.example.backend.entity.Board;
import com.example.backend.entity.Project;
import com.example.backend.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Task findByNo(Long no);
    List<Task> findAllByBoard(Board board);
    List<Task> findAllByPriority(int priority);

    /**
     * 프로젝트의 가장 최근 task 가져오기
     * @param project
     * @return Task
     */
    @Query(value = "SELECT t.* FROM task t INNER JOIN board b ON t.board_no = b.no WHERE b.project_no = ?1 ORDER BY t.no DESC LIMIT 1", nativeQuery = true)
    Task getLastByProject(Project project);

}
