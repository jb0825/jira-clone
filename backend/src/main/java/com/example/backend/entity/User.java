package com.example.backend.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;

    @NotNull
    @Column(length = 50)
    private String email;

    @NotNull
    @Column(length = 20)
    private String name;

    @NotNull
    @Column
    private String password;

    @Column(length = 20)
    private String position;

    @Column(length = 20)
    private String department;

    @Column(length = 20)
    private String company;

    @CreationTimestamp
    private Date createDate;
}
