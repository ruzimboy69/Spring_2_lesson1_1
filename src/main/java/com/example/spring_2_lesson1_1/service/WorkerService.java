package com.example.spring_2_lesson1_1.service;

import com.example.spring_2_lesson1_1.entity.Address;
import com.example.spring_2_lesson1_1.entity.Department;
import com.example.spring_2_lesson1_1.entity.Worker;
import com.example.spring_2_lesson1_1.payload.ApiResponse;
import com.example.spring_2_lesson1_1.payload.WorkerDto;
import com.example.spring_2_lesson1_1.repository.AddressRepository;
import com.example.spring_2_lesson1_1.repository.DepartmentRepository;
import com.example.spring_2_lesson1_1.repository.WorkerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WorkerService {
    final WorkerRepository workerRepository;
    final AddressRepository addressRepository;
    final DepartmentRepository departmentRepository;
    public ApiResponse getAll() {
        List<Worker> all = workerRepository.findAll();
        return new ApiResponse("success",true,all);
    }

    public ApiResponse getById(Integer id) {
        Optional<Worker> byId = workerRepository.findById(id);
        if(!byId.isPresent()){
            return new ApiResponse("not found",false);
        }
        Worker worker = byId.get();
        return new ApiResponse("found",true,worker);
    }

    public ApiResponse add(WorkerDto workerDto) {
        Optional<Worker> byPhoneNumber = workerRepository.findByPhoneNumber(workerDto.getPhoneNumber());
        if(byPhoneNumber.isPresent()){
            return new ApiResponse("Already exist",false);
        }
        Worker worker=new Worker();
        worker.setPhoneNumber(workerDto.getPhoneNumber());
        worker.setName(workerDto.getName());
        Optional<Address> byHomeNumberAndStreet = addressRepository.findByHomeNumberAndStreet(workerDto.getHomeNumber(), workerDto.getStreet());
        if(byHomeNumberAndStreet.isPresent()){
            return new ApiResponse("this address is busy",false);
        }
        Address address=new Address();
        address.setHomeNumber(workerDto.getHomeNumber());
        address.setStreet(workerDto.getStreet());
        addressRepository.save(address);
        worker.setAddress(address);
        Optional<Department> byId = departmentRepository.findById(workerDto.getDepartmentId());
        if(!byId.isPresent()){
            return new ApiResponse("not found",false);
        }
        Department department = byId.get();
        worker.setDepartment(department);
        Worker save = workerRepository.save(worker);
        return new ApiResponse("added",true,save);
    }

    public ApiResponse edit(Integer id, WorkerDto workerDto) {
        Optional<Worker> byId = workerRepository.findById(id);
        if(byId.isEmpty()){
            return new ApiResponse("not found",false);
        }
        Worker worker = byId.get();
        worker.setName(workerDto.getName());
        worker.setPhoneNumber(workerDto.getPhoneNumber());
        Optional<Address> byHomeNumberAndStreet = addressRepository.findByHomeNumberAndStreet(worker.getAddress().getHomeNumber(),worker.getAddress().getStreet());
        Address address = byHomeNumberAndStreet.get();
        address.setStreet(workerDto.getStreet());
        address.setHomeNumber(workerDto.getHomeNumber());
        worker.setAddress(address);
        Optional<Department> byId1 = departmentRepository.findById(workerDto.getDepartmentId());
        worker.setDepartment(byId1.get());
        Worker save = workerRepository.save(worker);
        return new ApiResponse("updated",true,save);
    }

    public ApiResponse delete(Integer id) {
        Optional<Worker> byId = workerRepository.findById(id);
        if (byId.isEmpty()) {
            return new ApiResponse("not found",false);
        }
        workerRepository.deleteById(id);
        return new ApiResponse("deleted",true);
    }
}
