package com.example.backend.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "task_pic")
public class TaskPIC {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_no")
    private User user;

    @ManyToOne(targetEntity = Task.class)
    @JoinColumn(name = "task_no")
    private Task task;
}
