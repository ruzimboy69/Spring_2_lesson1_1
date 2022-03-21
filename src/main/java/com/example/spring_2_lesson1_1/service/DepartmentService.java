package com.example.spring_2_lesson1_1.service;

import com.example.spring_2_lesson1_1.entity.Company;
import com.example.spring_2_lesson1_1.entity.Department;
import com.example.spring_2_lesson1_1.payload.ApiResponse;
import com.example.spring_2_lesson1_1.payload.DepartmentDto;
import com.example.spring_2_lesson1_1.repository.CompanyRepository;
import com.example.spring_2_lesson1_1.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    final DepartmentRepository departmentRepository;
    final CompanyRepository companyRepository;
    public ApiResponse getAll() {
        List<Department> all = departmentRepository.findAll();
        return new ApiResponse("success",true,all);
    }

    public ApiResponse getById(Integer id) {
        Optional<Department> byId = departmentRepository.findById(id);
        if(byId.isEmpty()){
            return new ApiResponse("not  found",false);
        }
        Department department = byId.get();
        return new ApiResponse("success",true,department);
    }

    public ApiResponse add(DepartmentDto departmentDto) {
//        Optional<Company> byId1 = companyRepository.findById(departmentDto.getCompanyId());
        Optional<Department> byNameAndCompany = departmentRepository.findByNameAndCompany_Id(departmentDto.getName(),departmentDto.getCompanyId());
        if (byNameAndCompany.isPresent()) {
            return new ApiResponse("already exist",false);
        }
        Department department=new Department();
        department.setName(departmentDto.getName());
        boolean b = companyRepository.existsById(departmentDto.getCompanyId());
        if(!b){
            return new ApiResponse("no company",false);
        }
        Optional<Company> byId = companyRepository.findById(departmentDto.getCompanyId());
        department.setCompany(byId.get());
        Department save = departmentRepository.save(department);
        return new ApiResponse("success",true,save);
    }

    public ApiResponse edit(Integer id, DepartmentDto departmentDto) {
        Optional<Department> byId = departmentRepository.findById(id);
        if(byId.isEmpty()){
            return new ApiResponse("failed",false);
        }
        Department department = byId.get();
        department.setName(departmentDto.getName());
        Optional<Company> byId1 = companyRepository.findById(departmentDto.getCompanyId());
        if(byId.isEmpty()){
            return new ApiResponse("company not found",false);
        }
        department.setCompany(byId1.get());
        Department save = departmentRepository.save(department);
        return new ApiResponse("success",true,save);
    }

    public ApiResponse delete(Integer id) {
        Optional<Department> byId = departmentRepository.findById(id);
        if(byId.isEmpty()){
            return new ApiResponse("not found",false);
        }
        departmentRepository.deleteById(id);
        return new ApiResponse("deleted",true);
    }
}
