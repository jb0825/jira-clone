package com.example.backend.repository;

import com.example.backend.entity.Board;
import com.example.backend.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Board findByNo(Long no);
    List<Board> findAllByProject(Project project);
}
