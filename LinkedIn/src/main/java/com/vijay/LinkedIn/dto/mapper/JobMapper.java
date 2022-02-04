package com.vijay.LinkedIn.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.vijay.LinkedIn.dto.model.AppliedJobDto;
import com.vijay.LinkedIn.dto.model.JobResponseDto;
import com.vijay.LinkedIn.entity.AppliedJobEntity;
import com.vijay.LinkedIn.entity.JobEntity;
import com.vijay.LinkedIn.entity.SavedJobEntity;

@Component
public class JobMapper {
	
	public List<JobResponseDto> toJobResponseDtos(List<JobEntity> jobs){
		
		List<JobResponseDto> jobResponseDtos = jobs.stream().map(job -> 
			new ModelMapper().map(job, JobResponseDto.class))
			.collect(Collectors.toList());
		return jobResponseDtos;
	}
	
	public List<AppliedJobDto> toAppliedJobDto(List<AppliedJobEntity> appliedJobs){
		List<AppliedJobDto> appliedJobsDtos = appliedJobs.stream().map(appliedJob -> 
				new ModelMapper().map(appliedJob, AppliedJobDto.class))
					.collect(Collectors.toList());
		return appliedJobsDtos;
	}

	public List<JobResponseDto> toJobResponseDtosFromAppliedJobs(
			List<AppliedJobEntity> appliedJobs) {
		List<JobEntity> jobs = appliedJobs.stream().map(appliedJob ->
			appliedJob.getJob())
				.collect(Collectors.toList());
		List<JobResponseDto> jobResponseDtos = toJobResponseDtos(jobs);
		return jobResponseDtos;
	}
	
	public List<JobResponseDto> toJobResponseDtosFromSavedJobs(
			List<SavedJobEntity> savedJobs) {
		List<JobResponseDto> jobResponseDtos = savedJobs.stream().map(savedJob -> 
				new ModelMapper().map(savedJob.getJob(), JobResponseDto.class))
					.collect(Collectors.toList());
		return jobResponseDtos;
		
	}
}
