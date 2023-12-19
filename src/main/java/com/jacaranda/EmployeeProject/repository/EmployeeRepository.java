package com.jacaranda.EmployeeProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jacaranda.EmployeeProject.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

}
