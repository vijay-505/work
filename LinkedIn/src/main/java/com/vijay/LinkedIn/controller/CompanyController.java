package com.vijay.LinkedIn.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vijay.LinkedIn.dto.model.CompanyDto;
import com.vijay.LinkedIn.entity.CompanyEntity;
import com.vijay.LinkedIn.service.CompanyService;

@RestController
public class CompanyController {
	
	@Autowired
	private CompanyService companyService; 
	
	@PostMapping("/companies")
	public ResponseEntity<CompanyDto> createCompany(@RequestBody CompanyEntity company) {
		return companyService.createCompany(company);
	}
	
	@GetMapping("/companies/{email}")
	public ResponseEntity<CompanyDto> createCompany(@PathVariable String email) {
		return companyService.retrieveCompany(email);
	}
	
	@PutMapping("/companies")
	public ResponseEntity<CompanyDto> updateCompany(
			@RequestParam MultipartFile profile,
			HttpServletRequest request) {
		return companyService.updateCompany(profile,request);
	}
}
