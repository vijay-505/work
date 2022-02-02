package com.vijay.LinkedIn.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.vijay.LinkedIn.dto.model.CompanyDto;
import com.vijay.LinkedIn.dto.model.CompanyRequestDto;

public interface CompanyService {

	ResponseEntity<CompanyDto> createCompany(CompanyRequestDto companyRequestDto);

	ResponseEntity<CompanyDto> retrieveCompany(String email);

	ResponseEntity<CompanyDto> updateCompany(MultipartFile profile, String email, HttpServletRequest request);

	ResponseEntity<byte[]> checkProfile(String email, HttpServletRequest request);

}
