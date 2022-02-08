package com.vijay.LinkedIn.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.vijay.LinkedIn.dto.model.CompanyDto;
import com.vijay.LinkedIn.dto.model.CompanyRequestDto;
import com.vijay.LinkedIn.dto.model.FollowerDto;

public interface CompanyService {

	ResponseEntity<CompanyDto> createCompany(CompanyRequestDto companyRequestDto);

	ResponseEntity<CompanyDto> retrieveCompany(String email);

	ResponseEntity<CompanyDto> updateCompany(MultipartFile profile, String email, HttpServletRequest request);

	ResponseEntity<byte[]> checkProfile(String email, HttpServletRequest request);

	ResponseEntity<FollowerDto> createFollower(String companyEmail, String userEmail);

	ResponseEntity<String> removeFollower(String companyEmail, String userEmail);

	ResponseEntity<FollowerDto> retrieveFollower(String companyEmail, String userEmail);

	ResponseEntity<List<FollowerDto>> retrieveAllFollower(String companyEmail);

}
