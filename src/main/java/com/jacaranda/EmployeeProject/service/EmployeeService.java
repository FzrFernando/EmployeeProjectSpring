package com.jacaranda.EmployeeProject.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jacaranda.EmployeeProject.model.Employee;
import com.jacaranda.EmployeeProject.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	public Page<Employee> getEmployees(int pageNum, int pageSize){
		Pageable pageable = PageRequest.of(pageNum -1, pageSize);
		return employeeRepository.findAll(pageable);
	}
	
	public Optional<Employee> getById(Integer id) {
		return employeeRepository.findById(id);
	}
	
	public Employee addEmployee(Employee e) {
		return employeeRepository.save(e);
	}
	
	public Employee editEmployee(Employee e) {
		Employee emp = getById(e.getId()).orElse(null);
		emp.setFirstName(e.getFirstName());
		emp.setLastName(e.getLastName());
		emp.setEmail(e.getEmail());
		emp.setGender(e.getGender());
		emp.setDateOfBirth(e.getDateOfBirth());
		emp.setIdCompany(e.getIdCompany());
		emp.setPassword(e.getPassword());
		emp.setRole(e.getRole());
		emp = employeeRepository.save(emp);
		return emp;
	}
}
