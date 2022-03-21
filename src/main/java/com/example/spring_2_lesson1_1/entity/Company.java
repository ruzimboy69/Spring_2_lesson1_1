package com.example.spring_2_lesson1_1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"corpName","directorName"}))
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String corpName;
    @Column(nullable = false)
    private String directorName;
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;
}
