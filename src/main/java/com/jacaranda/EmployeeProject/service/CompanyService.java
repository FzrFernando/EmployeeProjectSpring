package com.jacaranda.EmployeeProject.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.jacaranda.EmployeeProject.model.Company;
import com.jacaranda.EmployeeProject.repository.CompanyRepository;

@Service
public class CompanyService {

	@Autowired
	private CompanyRepository companyRepository;
	
	public Page<Company> getCompanies(int pageNum, int pageSize, String sortField){
		Pageable pageable = PageRequest.of(pageNum -1, pageSize, Sort.by(sortField).ascending());
		return companyRepository.findAll(pageable);
	}
	
	public Optional<Company> getById(Integer id) {
		return companyRepository.findById(id);
	}
	
	public Company addCompany(Company c) {
		return companyRepository.save(c);
	}
	
	public Company editCompany(Company c) {
		Company comp = getById(c.getId()).orElse(null);
		comp.setName(c.getName());
		comp.setAddress(c.getAddress());
		comp.setCity(c.getCity());
		comp = companyRepository.save(comp);
		return comp;
	}
}
