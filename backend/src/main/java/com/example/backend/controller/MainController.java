package com.example.backend.controller;

import com.example.backend.dto.BodyDto;
import com.example.backend.dto.Message;
import com.example.backend.dto.StatusCode;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @ApiOperation(value = "로그인 실패 반환", notes = "로그인 실패시 FORBIDDEN status 를 반환합니다.")
    @GetMapping("/forbidden")
    public ResponseEntity<BodyDto> loginError() {
        return new ResponseEntity<>(new BodyDto(StatusCode.FORBIDDEN, Message.FORBIDDEN, null), HttpStatus.FORBIDDEN);
    }
}
