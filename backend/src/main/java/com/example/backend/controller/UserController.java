package com.example.backend.controller;

import com.example.backend.dto.*;
import com.example.backend.entity.User;
import com.example.backend.service.UserService;
import com.example.backend.util.ResponseEntityCreator;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    public ResponseEntityCreator creator = new ResponseEntityCreator();

    @ApiOperation(value = "단일 사용자 조회", notes = "이메일로 사용자 정보를 조회합니다.")
    @GetMapping("/{email}")
    public ResponseEntity<BodyDto> getUserByEmail(@PathVariable String email) {
        UserDto user = userService.getUserByEmail(email);
        return creator.create(user);
    }

    @ApiOperation(value = "모든 사용자 조회", notes = "모든 사용자 정보를 패스워드를 제외하고 조회합니다.")
    @GetMapping
    public ResponseEntity<BodyDto> getAllUsers() {
        return creator.create(userService.getAllUsers());
    }

    @PostMapping("/sign-up")
    public ResponseEntity<BodyDto> signUp(@RequestBody User user) {
        BodyDto body = new BodyDto();
        ResponseEntity<BodyDto> badRequest = new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);

        if (user == null) {
            body.setMessage(Message.EMPTY_REQUEST);
            return badRequest;
        }
        if (!userService.signUp(user)) return badRequest;

        body.setStatus(StatusCode.CREATE);
        body.setMessage(Message.CREATE);
        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<BodyDto> signIn(@RequestBody User user, HttpSession session) {
        BodyDto body = new BodyDto();
        ResponseEntity<BodyDto> badRequest = new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);

        if (user == null) return badRequest;
        if (user.getEmail() == null || user.getPassword() == null) return badRequest;

        User loginUser = userService.signIn(user.getEmail(), user.getPassword());

        if (loginUser == null) {
            body.setMessage(Message.LOGIN_FAIL);
            return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
        }

        body.setStatus(StatusCode.OK);
        body.setMessage(Message.LOGIN_SUCCESS);

        session.setAttribute("login", loginUser);

        return new ResponseEntity<>(body, HttpStatus.OK);
    }

}
