package com.example.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString(exclude = "project")
@Entity
@Table(name = "board")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;

    @Column(length = 20)
    private String name;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "project_no")
    private Project project;

    @JsonManagedReference
    @OneToMany(mappedBy = "board")
    private List<Task> tasks = new ArrayList<>();
}
