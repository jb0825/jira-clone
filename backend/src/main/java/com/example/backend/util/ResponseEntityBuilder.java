package com.example.backend.util;

import com.example.backend.dto.BodyDto;
import com.example.backend.dto.Message;
import com.example.backend.dto.StatusCode;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Create a ResponseEntity with a BodyDto (status, message, data) for REST Controller.
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class ResponseEntityBuilder {

    private int successCode = StatusCode.OK;
    private int failCode = StatusCode.NOT_FOUND;
    private String successMsg = Message.READ;
    private String failMsg = Message.NOT_FOUND;

    /**
     * create READ body
     * @param obj the object to check null
     * @return ResponseEntity
     */
    public ResponseEntity<BodyDto> read(Object obj) {
        BodyDto body = new BodyDto();

        if (obj == null) {
            body.setMessage(failMsg);
            return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
        }

        body.setStatus(successCode);
        body.setMessage(successMsg);
        body.setData(obj);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

}
