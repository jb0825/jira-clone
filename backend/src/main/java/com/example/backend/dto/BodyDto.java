package com.example.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class BodyDto {
    private StatusEnum status;
    private MessageEnum message;
    private Object data;

    public BodyDto() {
        this.status = StatusEnum.BAD_REQUEST;
        this.message = MessageEnum.BAD_REQUEST;
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
