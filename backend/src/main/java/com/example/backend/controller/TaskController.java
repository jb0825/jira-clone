package com.example.backend.controller;

import com.example.backend.entity.Task;
import com.example.backend.entity.User;
import com.example.backend.service.TaskService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @ApiOperation(value = "단일 테스크 조회", notes = "테스크 번호로 단일 테스크를 조회합니다.")
    @GetMapping("/{no}")
    public ResponseEntity<Task> getTaskByNo(@PathVariable Long no) {
        ResponseEntity badRequest = new ResponseEntity(HttpStatus.BAD_REQUEST);
        if (no == null) return badRequest;

        Task task = taskService.getTaskByNo(no);
        return task != null ? new ResponseEntity<>(task, HttpStatus.OK) : badRequest;
    }

    @ApiOperation(value = "테스크로 담당자 조회", notes = "테스크 번호로 해당 테스크의 모든 담당자 조회")
    @GetMapping("/{no}/pic")
    public ResponseEntity<List<User>> getPICsByTask(@PathVariable Long no) {
        return null;
    }

    @PostMapping
    public ResponseEntity createTask(@RequestBody Task task) {
        ResponseEntity badRequest = new ResponseEntity(HttpStatus.BAD_REQUEST);
        if (task == null) return badRequest;

        return taskService.createTask(task) ? new ResponseEntity(HttpStatus.OK) : badRequest;
    }

    @DeleteMapping("/{no}")
    public ResponseEntity deleteTask(@PathVariable Long no) {
        ResponseEntity badRequest = new ResponseEntity(HttpStatus.BAD_REQUEST);
        if (no == null) return badRequest;

        return taskService.deleteTask(no) ? new ResponseEntity(HttpStatus.OK) : badRequest;
    }
}
