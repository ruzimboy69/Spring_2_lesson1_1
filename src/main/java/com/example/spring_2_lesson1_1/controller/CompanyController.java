package com.example.spring_2_lesson1_1.controller;

import com.example.spring_2_lesson1_1.entity.Company;
import com.example.spring_2_lesson1_1.payload.ApiResponse;
import com.example.spring_2_lesson1_1.payload.CompanyDto;
import com.example.spring_2_lesson1_1.repository.CompanyRepository;
import com.example.spring_2_lesson1_1.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    CompanyService companyService;
    @Autowired
    CompanyRepository companyRepository;
    @GetMapping
  public HttpEntity<?> getAll(){
        ApiResponse all = companyService.getAll();

        return ResponseEntity.status(all.isSuccess() ? HttpStatus.FOUND :HttpStatus.NOT_FOUND).body(all);
    }
    @GetMapping("/{id}")
    public HttpEntity<?> getOneById(@PathVariable Integer id){
        ApiResponse byId = companyService.getById(id);
        return ResponseEntity.status(byId.isSuccess() ? HttpStatus.FOUND :HttpStatus.NOT_FOUND).body(byId);
    }
    @PostMapping
    public HttpEntity<?> add(@RequestBody CompanyDto companyDto){
        ApiResponse add = companyService.add(companyDto);
        return ResponseEntity.status(add.isSuccess() ? HttpStatus.CREATED:HttpStatus.CONFLICT).body(add);
    }
    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id,@RequestBody CompanyDto companyDto){
        ApiResponse update = companyService.update(id, companyDto);
        return ResponseEntity.status(update.isSuccess() ? HttpStatus.ACCEPTED :HttpStatus.CONFLICT).body(update);
    }
    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id){
        ApiResponse delete = companyService.delete(id);
        return ResponseEntity.status(delete.isSuccess() ? HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(delete);
    }
}
