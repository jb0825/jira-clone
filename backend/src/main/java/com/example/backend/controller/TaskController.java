package com.example.backend.controller;

import com.example.backend.dto.BodyDto;
import com.example.backend.dto.Message;
import com.example.backend.dto.StatusCode;
import com.example.backend.entity.Task;
import com.example.backend.entity.User;
import com.example.backend.service.TaskPICService;
import com.example.backend.service.TaskService;
import com.example.backend.util.ResponseEntityCreator;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private TaskPICService taskPICService;

    public ResponseEntityCreator creator = new ResponseEntityCreator();

    @ApiOperation(value = "담당한 업무 조회", notes = "로그인한 사용자가 담당하고 있는 모든 업무를 조회합니다.")
    @GetMapping("/pic")
    public ResponseEntity<BodyDto> getTasksByUser(HttpSession session) {
        Long no = ((User) session.getAttribute("login")).getNo();
        return creator.create(taskPICService.getTasksByUser(no));
    }

    @ApiOperation(value = "단일 업무 조회", notes = "업무 번호로 단일 업무를 조회합니다.")
    @GetMapping("/{no}")
    public ResponseEntity<BodyDto> getTaskByNo(@PathVariable Long no) {
        return creator.create(taskService.getTaskByNo(no));
    }

    @ApiOperation(value = "업무로 담당자 조회", notes = "업무 번호로 해당 업무의 모든 담당자 조회")
    @GetMapping("/{no}/pic")
    public ResponseEntity<BodyDto> getPICsByTask(@PathVariable Long no) {
        return creator.create(taskPICService.getPICsByTask(no));
    }

    @ApiOperation(value = "업무 담당자 추가", notes = "특정 업무에서 해당 사용자 번호의 담당자를 추가합니다.")
    @PostMapping("/{taskNo}/pic/{userNo}")
    public ResponseEntity<BodyDto> addTaskPIC(@PathVariable Long taskNo, @PathVariable Long userNo) {
        BodyDto body = new BodyDto();

        if (!taskPICService.addTaskPIC(userNo, taskNo)) return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);

        body.setStatus(StatusCode.CREATE);
        body.setMessage(Message.CREATE);
        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }
    
    @ApiOperation(value = "업무 담당자 삭제", notes = "특정 업무에서 해당 사용자 번호의 담당자를 삭제합니다.")
    @DeleteMapping("/{taskNo}/pic/{userNo}")
    public ResponseEntity<BodyDto> deleteTaskPIC(@PathVariable Long taskNo, @PathVariable Long userNo) {
        BodyDto body = new BodyDto();
        
        if (!taskPICService.deleteTaskPIC(userNo, taskNo)) return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
        
        body.setStatus(StatusCode.OK);
        body.setMessage(Message.SUCCESS);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BodyDto> createTask(@RequestBody Task task, @RequestBody Long boardNo) {
        BodyDto body = new BodyDto();
        ResponseEntity<BodyDto> badRequest = new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);

        if (task == null) {
            body.setMessage(Message.EMPTY_REQUEST);
            return badRequest;
        }
        if (!taskService.createTask(task, boardNo)) return badRequest;

        body.setStatus(StatusCode.CREATE);
        body.setMessage(Message.CREATE);
        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }

    @DeleteMapping("/{no}")
    public ResponseEntity<BodyDto> deleteTask(@PathVariable Long no) {
        BodyDto body = new BodyDto();

        if (!taskService.deleteTask(no)) return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);

        body.setStatus(StatusCode.OK);
        body.setMessage(Message.SUCCESS);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }
}
