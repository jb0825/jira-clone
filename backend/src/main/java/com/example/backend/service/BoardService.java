package com.example.backend.service;

import com.example.backend.entity.Board;
import com.example.backend.entity.Project;
import com.example.backend.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private ProjectService projectService;

    public List<Board> getBoardsByProjectNo(Long no) {
        return boardRepository.findAllByProject(projectService.getProjectByNo(no));
    }

    public Board getBoardByNo(Long no) { return boardRepository.findByNo(no); }

    public boolean createBoard(Board board) {
        if (board == null) return false;

        boardRepository.save(board);
        return true;
    }

    public boolean deleteBoard(Long no) {
        Board board = boardRepository.findByNo(no);
        if (board == null) return false;

        boardRepository.delete(board);
        return true;
    }
}
