package com.example.spring_2_lesson1_1.controller;

import com.example.spring_2_lesson1_1.payload.ApiResponse;
import com.example.spring_2_lesson1_1.payload.WorkerDto;
import com.example.spring_2_lesson1_1.repository.WorkerRepository;
import com.example.spring_2_lesson1_1.service.WorkerService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/worker")
public class WorkerController {
    @Autowired
    WorkerService workerService;

    @GetMapping
    public HttpEntity<?> get(){
        ApiResponse all = workerService.getAll();
        return ResponseEntity.status(all.isSuccess() ? HttpStatus.FOUND : HttpStatus.NOT_FOUND).body(all);
    }
    @GetMapping("/{id}")
    public HttpEntity<?> getById(@PathVariable Integer id){
        ApiResponse byId = workerService.getById(id);
        return ResponseEntity.status(byId.isSuccess() ? HttpStatus.FOUND :HttpStatus.NOT_FOUND).body(byId);
    }
    @PostMapping
    public HttpEntity<?> add(@RequestBody WorkerDto workerDto){
        ApiResponse add = workerService.add(workerDto);
        return ResponseEntity.status(add.isSuccess()? HttpStatus.ACCEPTED :HttpStatus.CONFLICT).body(add);
    }
    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id,@RequestBody WorkerDto workerDto){
        ApiResponse edit = workerService.edit(id, workerDto);
        return ResponseEntity.status(edit.isSuccess() ? HttpStatus.ACCEPTED: HttpStatus.CONFLICT).body(edit);
    }
    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id){
        ApiResponse delete = workerService.delete(id);
        return ResponseEntity.status(delete.isSuccess() ? HttpStatus.ACCEPTED :HttpStatus.CONFLICT).body(delete);
    }
}
