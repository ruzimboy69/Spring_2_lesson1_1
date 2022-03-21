package com.example.spring_2_lesson1_1.repository;

import com.example.spring_2_lesson1_1.entity.Company;
import com.example.spring_2_lesson1_1.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department,Integer> {
    Optional<Department> findByNameAndCompany_Id(String name,Integer companyId);
}
