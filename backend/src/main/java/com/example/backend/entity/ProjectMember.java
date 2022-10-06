package com.example.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
