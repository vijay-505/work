package com.vijay.LinkedIn.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.vijay.LinkedIn.dto.model.AppliedJobDto;
import com.vijay.LinkedIn.dto.model.JobDto;
import com.vijay.LinkedIn.dto.model.JobResponseDto;
import com.vijay.LinkedIn.dto.model.SavedJobDto;

public interface JobService {

	ResponseEntity<JobDto> createJob(@Valid JobDto jobDto);

	ResponseEntity<JobDto> retrieveJob(int jobId);

	ResponseEntity<List<JobResponseDto>> retrieveAllJob(String email);

	ResponseEntity<String> closeJob(int jobId, String email);

	ResponseEntity<AppliedJobDto> applyJob(MultipartFile resume, int jobId, 
			HttpServletRequest request);

	ResponseEntity<byte[]> retriveUserResume(int jobId, String email);
	
	ResponseEntity<AppliedJobDto> retriveAppliedJob(int jobId, String email);

	ResponseEntity<List<AppliedJobDto>> retriveAllApplication(int jobId);

	ResponseEntity<List<JobResponseDto>> retrieveAllAppliedJob(String email);

	ResponseEntity<SavedJobDto> saveJob(int jobId, String email);

	ResponseEntity<JobResponseDto> retrieveSavedJob(int jobId, String email);

	ResponseEntity<List<JobResponseDto>> retrieveAllSavedJob(String email);

}
