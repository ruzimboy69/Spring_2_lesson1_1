package com.example.spring_2_lesson1_1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class WorkerDto {
    private String name;
    private String phoneNumber;
    private String homeNumber;
    private String street;
    private Integer departmentId;
}
