package com.vijay.LinkedIn.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vijay.LinkedIn.dto.mapper.CompanyMapper;
import com.vijay.LinkedIn.dto.mapper.JobMapper;
import com.vijay.LinkedIn.dto.mapper.UserMapper;
import com.vijay.LinkedIn.dto.model.CompanyDto;
import com.vijay.LinkedIn.dto.model.JobResponseDto;
import com.vijay.LinkedIn.dto.model.UserDto;
import com.vijay.LinkedIn.entity.CompanyEntity;
import com.vijay.LinkedIn.entity.JobEntity;
import com.vijay.LinkedIn.entity.UserEntity;
import com.vijay.LinkedIn.repository.CompanyRepository;
import com.vijay.LinkedIn.repository.JobRepository;
import com.vijay.LinkedIn.repository.UserRepository;
import com.vijay.LinkedIn.service.SearchService;

@Service
public class SearchServiceImpl implements SearchService{
	
	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JobRepository jobRepository;
	
	@Autowired
	private CompanyMapper companyMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private JobMapper jobMapper;

	@Override
	public ResponseEntity<List<CompanyDto>> retrieveAllCompanyByName(String name) {
		List<CompanyEntity> companies = companyRepository.findByNameContains(name);
		companyMapper.toCompanyDtos(companies);
		return new ResponseEntity<>(
				companyMapper.toCompanyDtos(companies), 
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<UserDto>> retrieveAllUsersByName(String name) {
		List<UserEntity> users = userRepository.findByNameContains(name);
		return new ResponseEntity<>(
				userMapper.toUserDtos(users), 
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<JobResponseDto>> retrieveAllJobsByTitle(String title) {
		List<JobEntity> jobs = jobRepository.findByTitleContains(title);
		return new ResponseEntity<>(
				jobMapper.toJobResponseDtos(jobs), 
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<JobResponseDto>> retrieveAllJobsByTitleAndLocation(
			String title, String location) {
		List<JobEntity> jobs = 
				jobRepository.findByTitleContainsAndLocationContains(title,location);
		return new ResponseEntity<>(
				jobMapper.toJobResponseDtos(jobs), 
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<JobResponseDto>> retrieveAllJobsByDate(String title, String date) {
		
		Date end_date = new Date();
		Date start_date = new Date();
		if(date.contains("past 24 hours")) {
			start_date.setTime(end_date.getTime()-24*60*60*1000);
			System.out.println("start_date: "+start_date+" end_date:"+end_date);
		}
		else {
			LocalDate startDate;
			LocalDate endDate = LocalDate.now();
			System.out.println("endDate: "+endDate);
			if(date.contains("past 24 hours")) {
				startDate = endDate.minusDays(1);
				System.out.println("startDate: "+startDate);
			}
			else if(date.contains("past week")) {
				startDate = endDate.minusWeeks(1);
				System.out.println("startDate: "+startDate);
			}
			else if(date.contains("past month")) {
				startDate = endDate.minusMonths(1);
				System.out.println("startDate: "+startDate);
			}
			else {
				startDate = endDate.minusYears(1);
				System.out.println("startDate: "+startDate);
			}
			start_date = Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
			System.out.println("start_date: "+start_date+" end_date:"+end_date);
		}
		
		List<JobEntity> jobs = 
				jobRepository.findByTitleContainsAndDateBetween(
						title, start_date, end_date);
		return new ResponseEntity<>(
				jobMapper.toJobResponseDtos(jobs), 
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<JobResponseDto>> retrieveAllJobsByFilter(
			String title, String location, String date) {
		List<JobResponseDto> JobResponseDtos = new ArrayList<>();
		
		if(location.equals("NA") && date.equals("NA")) {
			JobResponseDtos = retrieveAllJobsByTitle(title).getBody();
		}
		else if(location.equals("NA")) {
			JobResponseDtos = retrieveAllJobsByDate(title, date).getBody();
		}
		else if(date.equals("NA")) {
			JobResponseDtos = retrieveAllJobsByTitleAndLocation(title, location).getBody();
		}
		return new ResponseEntity<>(JobResponseDtos, HttpStatus.OK);
	}


}
