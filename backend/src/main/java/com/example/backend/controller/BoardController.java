package com.example.backend.controller;

import com.example.backend.entity.Board;
import com.example.backend.entity.Task;
import com.example.backend.service.BoardService;
import com.example.backend.service.TaskService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;
    @Autowired
    private TaskService taskService;

    @ApiOperation(value = "단일 보드 조회", notes = "보드 번호로 단일 보드를 조회합니다.")
    @GetMapping("/{no}")
    public ResponseEntity<Board> getBoardByNo(@PathVariable Long no) {
        ResponseEntity badRequest = new ResponseEntity(HttpStatus.BAD_REQUEST);
        if (no == null) return badRequest;

        Board board = boardService.getBoardByNo(no);
        return board != null ? new ResponseEntity<>(board, HttpStatus.OK) : badRequest;
    }

    @ApiOperation(value = "보드로 테스크 목록 조회", notes = "보드 번호로 소속된 모든 테스크를 조회합니다.")
    @GetMapping("/{no}/task")
    public ResponseEntity<List<Task>> getTasksByBoardNo(@PathVariable Long no) {
        if (no == null) return new ResponseEntity(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(taskService.getTasksByBoardNo(no), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createBoard(@RequestBody Board board) {
        ResponseEntity badRequest = new ResponseEntity(HttpStatus.BAD_REQUEST);
        if (board == null) return badRequest;

        return boardService.createBoard(board) ? new ResponseEntity(HttpStatus.OK) : badRequest;
    }

    @DeleteMapping("/{no}")
    public ResponseEntity deleteBoard(@PathVariable Long no) {
        ResponseEntity badRequest = new ResponseEntity(HttpStatus.BAD_REQUEST);
        if (no == null) return badRequest;

        return boardService.deleteBoard(no) ? new ResponseEntity(HttpStatus.OK) : badRequest;
    }
}
