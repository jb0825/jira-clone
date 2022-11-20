package com.example.backend.dto;

public enum MessageEnum {
    BAD_REQUEST("잘못된 요청입니다.", "BAD_REQUEST"),
    LOGIN_SUCCESS("로그인 성공", "LOGIN_SUCCESS"),
    LOGIN_FAIL("이메일 혹은 비밀번호가 틀렸습니다.", "LOGIN_FAIL"),
    READ_USER("회원 조회 성공", "READ_USER"),
    NOT_FOUNT_USER("회원을 찾을 수 없습니다.", "NOT_FOUNT_USER"),
    CREATE_USER("회원가입 성공", "CREATE_USER");

    String message;
    String code;

    MessageEnum(String message, String code) {
        this.message = message;
        this.code = code;
    }
}
