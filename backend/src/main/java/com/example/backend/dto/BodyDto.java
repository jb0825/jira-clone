package com.example.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class BodyDto {
    private int status;
    private String message;
    private Object data;

    public BodyDto() {
        this.status = StatusCode.BAD_REQUEST;
        this.message = Message.BAD_REQUEST;
        this.data = null;
    }

    @Override
    public String toString() {
        return "{\n" +
                "   status: " + status + "\n" +
                "   message: " + message + "\n" +
                "   data: " + data + "\n" +
                "}";
    }
}
