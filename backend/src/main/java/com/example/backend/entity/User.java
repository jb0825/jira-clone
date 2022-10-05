package com.example.backend.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long no;

    @Column(length = 10)
    private String name;

    @Column(length = 50)
    private String email;

    private String password;

    @Column(length = 20)
    private String post;

    @Column(length = 20)
    private String department;

    @Column(length = 20)
    private String company;

    @CreatedDate
    private Date createDate;
}
