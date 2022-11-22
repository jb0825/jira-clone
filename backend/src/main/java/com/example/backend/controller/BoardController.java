package com.example.backend.controller;

import com.example.backend.dto.BodyDto;
import com.example.backend.dto.Message;
import com.example.backend.dto.StatusCode;
import com.example.backend.entity.Board;
import com.example.backend.service.BoardService;
import com.example.backend.service.TaskService;
import com.example.backend.util.ResponseEntityCreator;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;
    @Autowired
    private TaskService taskService;

    public ResponseEntityCreator creator = new ResponseEntityCreator();

    @ApiOperation(value = "단일 보드 조회", notes = "보드 번호로 단일 보드를 조회합니다.")
    @GetMapping("/{no}")
    public ResponseEntity<BodyDto> getBoardByNo(@PathVariable Long no) {
        return creator.create(boardService.getBoardByNo(no));
    }

    @ApiOperation(value = "보드로 업무 목록 조회", notes = "보드 번호로 소속된 모든 업무를 조회합니다.")
    @GetMapping("/{no}/task")
    public ResponseEntity<BodyDto> getTasksByBoardNo(@PathVariable Long no) {
        return creator.create(taskService.getTasksByBoardNo(no));
    }

    @PostMapping
    public ResponseEntity<BodyDto> createBoard(@RequestBody Board board) {
        BodyDto body = new BodyDto();
        ResponseEntity<BodyDto> badRequest = new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);

        if (board == null) return badRequest;
        if (!boardService.createBoard(board)) return badRequest;

        body.setStatus(StatusCode.CREATE);
        body.setMessage(Message.CREATE);
        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }

    @DeleteMapping("/{no}")
    public ResponseEntity<BodyDto> deleteBoard(@PathVariable Long no) {
        BodyDto body = new BodyDto();

        if (!boardService.deleteBoard(no)) return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);

        body.setStatus(StatusCode.OK);
        body.setMessage(Message.SUCCESS);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }
}
