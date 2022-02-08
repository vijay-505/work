package com.vijay.LinkedIn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.vijay.LinkedIn.dto.model.CompanyDto;
import com.vijay.LinkedIn.dto.model.JobResponseDto;
import com.vijay.LinkedIn.dto.model.UserDto;
import com.vijay.LinkedIn.service.SearchService;

@RestController
public class SearchController {
	
	@Autowired
	private SearchService searchService;
	
	@GetMapping("/search/companies/{name}")
	public ResponseEntity<List<CompanyDto>> retrieveAllCompanyByName(
			@PathVariable String name){
		return searchService.retrieveAllCompanyByName(name);
	}
	
	@GetMapping("/search/users/{name}")
	public ResponseEntity<List<UserDto>> retrieveAllUsersByName(
			@PathVariable String name){
		return searchService.retrieveAllUsersByName(name);
	}
	
	@GetMapping("/search/jobs/{title}")
	public ResponseEntity<List<JobResponseDto>> retrieveAllJobsByTitle(
			@PathVariable String title){
		return searchService.retrieveAllJobsByTitle(title);
	}
	
	@GetMapping("/search/jobs/{title}/locations/{location}")
	public ResponseEntity<List<JobResponseDto>> retrieveAllJobsByTitleAndLocation(
			@PathVariable String title,
			@PathVariable String location){
		return searchService.retrieveAllJobsByTitleAndLocation(title, location);
	}
	
	@GetMapping("/search/jobs/{title}/date/{date}")
	public ResponseEntity<List<JobResponseDto>> retrieveAllJobsByDate(
			@PathVariable String title,
			@PathVariable String date){
		return searchService.retrieveAllJobsByDate(title, date);
	}
	
	@GetMapping("/search/jobs/{title}/locations/{location}/date/{date}")
	public ResponseEntity<List<JobResponseDto>> retrieveAllJobsByFilter(
			@PathVariable String title,
			@PathVariable String location,
			@PathVariable String date){
		return searchService.retrieveAllJobsByFilter(title, location, date);
	}

}
