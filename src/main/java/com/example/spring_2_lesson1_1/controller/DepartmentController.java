package com.example.spring_2_lesson1_1.controller;

import com.example.spring_2_lesson1_1.payload.ApiResponse;
import com.example.spring_2_lesson1_1.payload.DepartmentDto;
import com.example.spring_2_lesson1_1.repository.DepartmentRepository;
import com.example.spring_2_lesson1_1.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/department")
public class DepartmentController {
    final DepartmentRepository departmentRepository;
    final DepartmentService departmentService;
@GetMapping
    public HttpEntity<?> getAll(){
    ApiResponse all = departmentService.getAll();
    return ResponseEntity.status(all.isSuccess() ? HttpStatus.FOUND:HttpStatus.NOT_FOUND).body(all);
}
@GetMapping("/{id}")
    public HttpEntity<?> getById(@PathVariable Integer id){
    ApiResponse byId = departmentService.getById(id);
    return ResponseEntity.status(byId.isSuccess() ? HttpStatus.FOUND :HttpStatus.NOT_FOUND).body(byId);
}
@PostMapping
    public HttpEntity<?> add(@RequestBody DepartmentDto departmentDto){
    ApiResponse add = departmentService.add(departmentDto);
    return ResponseEntity.status(add.isSuccess() ? HttpStatus.CREATED :HttpStatus.CONFLICT).body(add);
}
@PutMapping("/{id}")
    public  HttpEntity<?> edit(@PathVariable Integer id,@RequestBody DepartmentDto departmentDto){
    ApiResponse edit = departmentService.edit(id, departmentDto);
    return ResponseEntity.status(edit.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(edit);
}
@DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id){
    ApiResponse delete = departmentService.delete(id);
    return ResponseEntity.status(delete.isSuccess() ? HttpStatus.ACCEPTED :HttpStatus.CONFLICT).body(delete);
}
}
