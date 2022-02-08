package com.vijay.LinkedIn.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vijay.LinkedIn.dto.model.AppliedJobDto;
import com.vijay.LinkedIn.dto.model.JobDto;
import com.vijay.LinkedIn.dto.model.JobResponseDto;
import com.vijay.LinkedIn.dto.model.SavedJobDto;
import com.vijay.LinkedIn.service.JobService;

@RestController
public class JobController {

	@Autowired
	private JobService jobService;
	
	@PostMapping("/jobs")
	public ResponseEntity<JobDto> createJob(
			@Valid @RequestBody JobDto jobDto){
		return jobService.createJob(jobDto);
	}
	
	@GetMapping("/jobs/{jobId}")
	public ResponseEntity<JobDto> retrieveJob(
			@PathVariable int jobId){
		return jobService.retrieveJob(jobId);
	}
	
	@GetMapping("/jobs/companies/{email}")
	public ResponseEntity<List<JobResponseDto>> retrieveAllJob(
			@PathVariable String email){
		return jobService.retrieveAllJob(email);
	}
	
	@DeleteMapping("/jobs/{jobId}/{email}")
	public ResponseEntity<String> closeJob(
			@PathVariable int jobId,
			@PathVariable String email){
		return jobService.closeJob(jobId, email);
	}
	
	@PostMapping("/jobs/{jobId}")
	public ResponseEntity<AppliedJobDto> applyJob(
			@RequestParam MultipartFile resume,
			@PathVariable int jobId, 
			HttpServletRequest request){
		return jobService.applyJob(resume, jobId, request);
	}
	
	@GetMapping("/jobs/{jobId}/users/{email}/resume")
	public ResponseEntity<byte[]> retriveUserResume(
			@PathVariable int jobId,
			@PathVariable String email){
		return jobService.retriveUserResume(jobId, email);
	}
	
	@GetMapping("/jobs/{jobId}/users/{email}/applied")
	public ResponseEntity<AppliedJobDto> retriveAppliedJob(
			@PathVariable int jobId,
			@PathVariable String email){
		return jobService.retriveAppliedJob(jobId, email);
	}
	
	@GetMapping("/jobs/{jobId}/users")
	public ResponseEntity<List<AppliedJobDto>> retriveAllApplication(
			@PathVariable int jobId){
		return jobService.retriveAllApplication(jobId);
	}
	
	@GetMapping("/jobs/users/{email}")
	public ResponseEntity<List<JobResponseDto>> retrieveAllAppliedJob(
			@PathVariable String email){
		return jobService.retrieveAllAppliedJob(email);
	}
	
	@PostMapping("/jobs/{jobId}/users/{email}/save")
	public ResponseEntity<SavedJobDto> saveJob(@PathVariable int jobId,
			@PathVariable String email){
		return jobService.saveJob(jobId, email);
	}
	
	@GetMapping("/jobs/{jobId}/users/{email}/save")
	public ResponseEntity<JobResponseDto> retrieveSavedJob(
			@PathVariable int jobId,
			@PathVariable String email){
		return jobService.retrieveSavedJob(jobId, email);
	}
	
	@GetMapping("/jobs/users/{email}/save")
	public ResponseEntity<List<JobResponseDto>> retrieveAllSavedJob(
			@PathVariable String email){
		return jobService.retrieveAllSavedJob(email);
	}
	
}
