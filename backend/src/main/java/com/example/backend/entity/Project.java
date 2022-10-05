package com.example.backend.entity;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long no;

    @Column
    private String name;

    @CreatedDate
    private Date createDate;

}
