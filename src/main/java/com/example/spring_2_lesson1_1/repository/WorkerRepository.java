package com.example.spring_2_lesson1_1.repository;

import com.example.spring_2_lesson1_1.entity.Worker;
import org.hibernate.jdbc.Work;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WorkerRepository extends JpaRepository<Worker,Integer> {
Optional<Worker> findByPhoneNumber(String phoneNumber);
}
