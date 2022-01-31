package com.vijay.LinkedIn.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.vijay.LinkedIn.dto.model.CompanyDto;
import com.vijay.LinkedIn.entity.CompanyEntity;

public interface CompanyService {

	ResponseEntity<CompanyDto> createCompany(CompanyEntity company);

	ResponseEntity<CompanyDto> retrieveCompany(String email);

	ResponseEntity<CompanyDto> updateCompany(MultipartFile profile, HttpServletRequest request);

}
