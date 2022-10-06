package com.example.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;

    @Column(length = 20)
    private String title;

    @Column
    private String description;

    @Column(length = 20)
    private String taskKey;

    // 1 highest
    // 2 high
    // 3 medium
    // 4 low
    // 5 lowest
    @Column(columnDefinition = "tinyint default 3")
    private int priority = 3;

    @Column
    private Date startDate;

    @Column
    private Date endDate;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "board_no")
    private Board board;
}
