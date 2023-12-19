package com.jacaranda.EmployeeProject.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.jacaranda.EmployeeProject.model.Company;
import com.jacaranda.EmployeeProject.service.CompanyService;

@Controller
public class CompanyController {

	@Autowired
	private CompanyService companyService;
	
	
	@GetMapping("/listCompanies")
	public String listCompanies(Model model, @RequestParam("pageNumber") Optional<Integer> pageNumber,
			@RequestParam("sizeNumber") Optional<Integer> sizeNumber) {
		Page<Company> page = companyService.getCompanies(pageNumber.orElse(1), sizeNumber.orElse(10));
		model.addAttribute("listCompanies", page);
		model.addAttribute("currentPage",pageNumber.orElse(1));
		model.addAttribute("totalPages",page.getTotalPages());
		model.addAttribute("totalItems",page.getTotalElements());
		return "listCompanies";
	}
	
	@GetMapping("/addCompany")
	public String addCompany(Model model) {
		Company c = new Company();
		model.addAttribute("c", c);
		return "addCompany";
	}
	
	@GetMapping("/addCompanyDone")
	public String addCompanyDone(Model model, Company c) {
		companyService.addCompany(c);
		Company company = new Company();
		model.addAttribute("c", company);
		String result = "La compañía ha sido añadida con éxito";
		model.addAttribute("result", result);
		return "addCompany";
	}
	
	@GetMapping("/editCompany")
	public String editCompany(@RequestParam Integer id, Model model) {
		Optional<Company> company = companyService.getById(id);
		if (company != null) {
			model.addAttribute("c", company);
		}
		return "editCompany";
	}
	
	@GetMapping("/editCompanyDone")
	public String editCompanyDone(@ModelAttribute("c") Company cUpdate, Model model) {
		Company comp = new Company();
		
		model.addAttribute("c", comp);
		companyService.editCompany(cUpdate);
		String result = "La compañía ha sido editada con éxito";
		model.addAttribute("result", result);
		return "editCompany";
	}
}
