package com.example.spring_2_lesson1_1.repository;

import com.example.spring_2_lesson1_1.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


public interface CompanyRepository extends JpaRepository<Company,Integer> {
    boolean existsByCorpNameAndDirectorName(String corpName,String directorName);
}
