package com.jacaranda.EmployeeProject.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.jacaranda.EmployeeProject.model.Employee;
import com.jacaranda.EmployeeProject.service.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/listEmployees")
	public String listEmployees(Model model, @RequestParam("pageNumber") Optional<Integer> pageNumber,
			@RequestParam("sizeNumber") Optional<Integer> sizeNumber) {
		Page<Employee> page = employeeService.getEmployees(pageNumber.orElse(1), sizeNumber.orElse(10));
		model.addAttribute("listEmployees", page);
		model.addAttribute("currentPage",pageNumber.orElse(1));
		model.addAttribute("totalPages",page.getTotalPages());
		model.addAttribute("totalItems",page.getTotalElements());
		return "listEmployees";
	}
	
	@GetMapping("/addEmployee")
	public String addEmployee(Model model) {
		Employee e = new Employee();
		model.addAttribute("e", e);
		return "addEmployee";
	}
	
	@GetMapping("/addEmployeeDone")
	public String addEmployeeDone(Model model, Employee e) {
		employeeService.addEmployee(e);
		Employee Employee = new Employee();
		model.addAttribute("e", Employee);
		String result = "El empleado ha sido añadido con éxito";
		model.addAttribute("result", result);
		return "addEmployee";
	}
	
	@GetMapping("/editEmployee")
	public String editEmployee(@RequestParam Integer id, Model model) {
		Optional<Employee> Employee = employeeService.getById(id);
		if (Employee != null) {
			model.addAttribute("e", Employee);
		}
		return "editEmployee";
	}
	
	@GetMapping("/editEmployeeDone")
	public String editEmployeeDone(@ModelAttribute("e") Employee eUpdate, Model model) {
		Employee emp = new Employee();
		
		model.addAttribute("e", emp);
		employeeService.editEmployee(eUpdate);
		String result = "El empleado ha sido editado con éxito";
		model.addAttribute("result", result);
		return "editEmployee";
	}
}
