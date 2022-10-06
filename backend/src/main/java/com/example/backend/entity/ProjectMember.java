package com.example.backend.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "project_member")
public class ProjectMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_no")
    private User user;

    @ManyToOne(targetEntity = Project.class)
    @JoinColumn(name = "project_no")
    private Project project;
}
