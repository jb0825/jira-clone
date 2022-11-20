package com.example.backend.dto;

// 굳이 enum 으로 할필요가 있을까? 그냥 final 변수 클래스로 만들기
public enum StatusEnum {
    OK(200, "OK"),
    CREATED(201, "CREATED"),
    BAD_REQUEST(400, "BAD_REQUEST"),
    FORBIDDEN(403, "FORBIDDEN"),
    NOT_FOUNT(404, "NOT_FOUNT"),
    INTERNAL_SERVER_ERROR(500, "INTERNAL_SERVER_ERROR"),
    DB_ERROR(600, "DB_ERROR");

    int statusCode;
    String code;

    StatusEnum(int statusCode, String code) {
        this.statusCode = statusCode;
        this.code = code;
    }
}
