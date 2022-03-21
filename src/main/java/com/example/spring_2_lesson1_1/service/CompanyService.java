package com.example.spring_2_lesson1_1.service;

import com.example.spring_2_lesson1_1.entity.Address;
import com.example.spring_2_lesson1_1.entity.Company;
import com.example.spring_2_lesson1_1.payload.ApiResponse;
import com.example.spring_2_lesson1_1.payload.CompanyDto;
import com.example.spring_2_lesson1_1.repository.AddressRepository;
import com.example.spring_2_lesson1_1.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
//@RequiredArgsConstructor
public class CompanyService {
   @Autowired
   CompanyRepository companyRepository;
   @Autowired
   AddressRepository addressRepository;
    public ApiResponse getAll() {
        List<Company> all = companyRepository.findAll();
        return new ApiResponse("list",true,all);

    }

    public ApiResponse getById(Integer id) {
        Optional<Company> byId = companyRepository.findById(id);
        if(byId.isPresent()){
            Company company = byId.get();
            return new ApiResponse("one",true,company);
        }
        return new ApiResponse("fail",false);
    }

    public ApiResponse add(CompanyDto companyDto) {
        boolean b = companyRepository.existsByCorpNameAndDirectorName(companyDto.getCorpName(), companyDto.getDirectorName());
        if(b){
            return new ApiResponse("already exist" ,false);
        }
        Company company1=new Company();
        company1.setCorpName(companyDto.getCorpName());
        company1.setDirectorName(companyDto.getDirectorName());
        Address address=new Address();
        address.setHomeNumber(companyDto.getHomeNumber());
        address.setStreet(companyDto.getStreet());
        addressRepository.save(address);
        company1.setAddress(address);
        Company save = companyRepository.save(company1);
        return new ApiResponse("added",true,save);
    }

    public ApiResponse update(Integer id, CompanyDto companyDto) {
        Optional<Company> byId = companyRepository.findById(id);
        if(byId.isEmpty()){
            return new ApiResponse("failed",false);
        }
        Company company = byId.get();
        company.setCorpName(companyDto.getCorpName());
        company.setDirectorName(companyDto.getDirectorName());
        Optional<Address> byHomeNumberAndSAndStreet = addressRepository.findByHomeNumberAndStreet(company.getAddress().getHomeNumber(), company.getAddress().getStreet());
        Address address = byHomeNumberAndSAndStreet.get();
        address.setHomeNumber(companyDto.getHomeNumber());
        address.setStreet(companyDto.getStreet());
        Address save1 = addressRepository.save(address);
        company.setAddress(save1);
        Company save = companyRepository.save(company);
        return new ApiResponse("added",true,save);
    }

    public ApiResponse delete(Integer id) {
        Optional<Company> byId = companyRepository.findById(id);
        if(byId.isEmpty()){
            return new ApiResponse("failed",false);
        }
        companyRepository.deleteById(id);
        return new ApiResponse("deleted",true);
    }
}
