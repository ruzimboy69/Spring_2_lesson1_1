package com.example.spring_2_lesson1_1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @NotNull
    @Column(unique = true)
    private String phoneNumber;
    @OneToOne
    private Address address;
    @ManyToOne
    private Department department;
}
