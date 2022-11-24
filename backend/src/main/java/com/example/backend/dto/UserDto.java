package com.example.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {
    private Long no;
    private String email;
    private String name;
    private String position;
    private String department;
    private String company;
}
