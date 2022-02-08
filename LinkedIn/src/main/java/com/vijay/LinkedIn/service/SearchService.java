package com.vijay.LinkedIn.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.vijay.LinkedIn.dto.model.CompanyDto;
import com.vijay.LinkedIn.dto.model.JobResponseDto;
import com.vijay.LinkedIn.dto.model.UserDto;

public interface SearchService {

	ResponseEntity<List<CompanyDto>> retrieveAllCompanyByName(String name);

	ResponseEntity<List<UserDto>> retrieveAllUsersByName(String name);

	ResponseEntity<List<JobResponseDto>> retrieveAllJobsByTitle(String title);

	ResponseEntity<List<JobResponseDto>> retrieveAllJobsByTitleAndLocation(
			String title, String location);

	ResponseEntity<List<JobResponseDto>> retrieveAllJobsByDate(String title, String date);

	ResponseEntity<List<JobResponseDto>> retrieveAllJobsByFilter(
			String title, String location, String date);

}
