package com.example.spring_2_lesson1_1.repository;

import com.example.spring_2_lesson1_1.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address ,Integer> {
    Optional<Address> findByHomeNumberAndStreet(String homeNumber,String street);
}
