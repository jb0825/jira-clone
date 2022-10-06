package com.example.backend.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@ToString(exclude = "boards")
@Entity
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;

    @Column(length = 20)
    private String name;

    @CreationTimestamp
    private Date createDate;

    @OneToOne
    @JoinColumn(name = "leader")
    private User leader;

    @JsonManagedReference
    @OneToMany(mappedBy = "project")
    private List<Board> boards = new ArrayList<>();
}
