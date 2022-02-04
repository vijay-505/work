package com.vijay.LinkedIn.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vijay.LinkedIn.dto.mapper.JobMapper;
import com.vijay.LinkedIn.dto.model.AppliedJobDto;
import com.vijay.LinkedIn.dto.model.JobDto;
import com.vijay.LinkedIn.dto.model.JobResponseDto;
import com.vijay.LinkedIn.dto.model.SavedJobDto;
import com.vijay.LinkedIn.entity.CompanyEntity;
import com.vijay.LinkedIn.entity.AppliedJobEntity;
import com.vijay.LinkedIn.entity.JobEntity;
import com.vijay.LinkedIn.entity.SavedJobEntity;
import com.vijay.LinkedIn.entity.UserEntity;
import com.vijay.LinkedIn.exception.AlreadyAppliedException;
import com.vijay.LinkedIn.exception.AlreadySavedException;
import com.vijay.LinkedIn.exception.FileErrorException;
import com.vijay.LinkedIn.exception.FileIOException;
import com.vijay.LinkedIn.exception.PermissionDeniedException;
import com.vijay.LinkedIn.repository.CompanyRepository;
import com.vijay.LinkedIn.repository.AppliedJobRepository;
import com.vijay.LinkedIn.repository.JobRepository;
import com.vijay.LinkedIn.repository.SavedJobRepository;
import com.vijay.LinkedIn.repository.UserRepository;
import com.vijay.LinkedIn.service.JobService;

@Service
public class JobServiceImpl implements JobService{

	@Autowired
	private JobRepository jobRepository;
	
	@Autowired
	private AppliedJobRepository appliedJobRepository;
	
	@Autowired
	private SavedJobRepository savedJobRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private JobMapper jobMapper;
	
	@Override
	public ResponseEntity<JobDto> createJob(@Valid JobDto jobDto) {
		JobEntity job = modelMapper.map(jobDto, JobEntity.class);
		Optional<UserEntity> optinalUser = 
				userRepository.findByEmail(jobDto.getUserEmail());
		if(optinalUser.isPresent()) {
			job.setUser(optinalUser.get());
		}
		CompanyEntity company = 
				companyRepository.findByEmail(jobDto.getCompanyEmail()).get();
		job.setCompany(company);
		job.setDate(new Date());
		jobRepository.save(job);
		return new ResponseEntity<>(
				modelMapper.map(job, JobDto.class),HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<JobDto> retrieveJob(int jobId) {
		return new ResponseEntity<>(
				modelMapper.map(jobRepository.getById(jobId), JobDto.class),
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<JobResponseDto>> retrieveAllJob(String email) {
		CompanyEntity company = 
				companyRepository.findByEmail(email).get();
		List<JobResponseDto> jobResponseDtos = jobMapper.toJobResponseDtos(company.getJobs());
		return new ResponseEntity<>(jobResponseDtos,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> closeJob(int jobId, String email) {
		JobEntity job = jobRepository.getById(jobId);
		Optional<UserEntity> optinalUser = Optional.ofNullable(job.getUser());
		String userEmail = "";
		if(optinalUser.isPresent()) {
			userEmail = optinalUser.get().getEmail();
		}
		if(!(userEmail.equals(email) || 
				job.getCompany().getEmail().equals(email))) {
			throw new PermissionDeniedException("you can't delete job");
		}
		jobRepository.deleteById(jobId);
		return new ResponseEntity<>("job closed successfully",HttpStatus.OK);
	}

	@Override
	public ResponseEntity<AppliedJobDto> applyJob(MultipartFile resume, int jobId, 
			HttpServletRequest request) {
		
		String email = request.getParameter("email");
		Optional<AppliedJobEntity> optionalAppliedJob = appliedJobRepository.findByUserEmailAndJobJobId(email,jobId);
		if(optionalAppliedJob.isPresent()) {
			throw new AlreadyAppliedException("you already applied for this job");
		}
		AppliedJobEntity appliedJob = new AppliedJobEntity();
		appliedJob.setJob(jobRepository.getById(jobId));
		appliedJob.setUser(userRepository.findByEmail(email).get());
		appliedJob.setPhone(request.getParameter("phone"));
		
		String contentType = resume.getContentType();
		if(!contentType.equals("application/pdf")) {
			throw new FileErrorException("it's not an pdf format");
		}
		try {
			appliedJob.setResume(resume.getBytes());
		} catch (IOException e) {
			throw new FileIOException("problem with uploading resume");
		}
		String resumeUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("jobs/"+jobId+"/"+email+"/resume")
				.toUriString();
		appliedJob.setResumeUrl(resumeUrl);
		
		appliedJobRepository.save(appliedJob);
		return new ResponseEntity<>(
				modelMapper.map(appliedJob, AppliedJobDto.class),
				HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<byte[]> retriveUserResume(int jobId, String email) {
		AppliedJobEntity appliedJob = 
				appliedJobRepository.findByUserEmailAndJobJobId(email,jobId).get();
		String mimeType = "application/pdf";
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(mimeType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName="+"resume") 
				.body(appliedJob.getResume());
	}

	@Override
	public ResponseEntity<List<AppliedJobDto>> retriveAllApplication(int jobId) {
		JobEntity job = jobRepository.getById(jobId);
		List<AppliedJobDto> appliedJobDtos = jobMapper.toAppliedJobDto(job.getAppliedJobs());
		return new ResponseEntity<>(appliedJobDtos,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<JobResponseDto>> retrieveAllAppliedJob(String email) {
		UserEntity user = userRepository.findByEmail(email).get();
		List<JobResponseDto> jobResponseDtos = 
				jobMapper.toJobResponseDtosFromAppliedJobs(user.getAppliedJobs());
		return new ResponseEntity<>(jobResponseDtos,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<SavedJobDto> saveJob(int jobId, String email) {
		Optional<SavedJobEntity> optinalSavedjob = 
				savedJobRepository.findByUserEmailAndJobJobId(email, jobId);
		if(optinalSavedjob.isPresent()) {
			throw new AlreadySavedException("job already saved");
		}
		SavedJobEntity savedJobEntity =new SavedJobEntity(); 
		userRepository.findByEmail(email);
		savedJobEntity.setJob(jobRepository.getById(jobId));
		savedJobEntity.setUser(userRepository.findByEmail(email).get());
		savedJobRepository.save(savedJobEntity);
		return new ResponseEntity<>(
				modelMapper.map(savedJobEntity, SavedJobDto.class),
				HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<JobResponseDto> retrieveSavedJob(int jobId, String email) {
		SavedJobEntity savedJob = 
				savedJobRepository.findByUserEmailAndJobJobId(email, jobId).get();
		return new ResponseEntity<>(
				modelMapper.map(savedJob.getJob(), JobResponseDto.class),
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<JobResponseDto>> retrieveAllSavedJob(String email) {
		UserEntity user = userRepository.findByEmail(email).get();
		List<JobResponseDto> jobResponseDtos = 
				jobMapper.toJobResponseDtosFromSavedJobs(user.getSavedJobs());
		return new ResponseEntity<>(jobResponseDtos,HttpStatus.OK);
	}

}
