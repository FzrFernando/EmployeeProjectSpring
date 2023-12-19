package com.jacaranda.EmployeeProject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jacaranda.EmployeeProject.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer>{

}
