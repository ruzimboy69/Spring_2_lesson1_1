package com.example.spring_2_lesson1_1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CompanyDto {
    private String corpName;
    private String directorName;
    private String street;
    private String homeNumber;

}
