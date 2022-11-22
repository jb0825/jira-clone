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
public class ResponseEntityCreator {

    /**
     * create ResponseEntity for [READ] action
     * @param obj the object to check null
     * @return ResponseEntity
     */
    public ResponseEntity<BodyDto> create(Object obj) {
        BodyDto body = new BodyDto();

        if (obj == null) {
            body.setMessage(Message.NOT_FOUND);
            return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
        }

        body.setStatus(StatusCode.OK);
        body.setMessage(Message.READ);
        body.setData(obj);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

}
