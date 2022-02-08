package com.vijay.LinkedIn.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vijay.LinkedIn.dto.mapper.FollowerMapper;
import com.vijay.LinkedIn.dto.model.CompanyDto;
import com.vijay.LinkedIn.dto.model.CompanyRequestDto;
import com.vijay.LinkedIn.dto.model.FollowerDto;
import com.vijay.LinkedIn.entity.CompanyEntity;
import com.vijay.LinkedIn.entity.FollowerEntity;
import com.vijay.LinkedIn.entity.UserEntity;
import com.vijay.LinkedIn.exception.FollowerAlreadyExistException;
import com.vijay.LinkedIn.exception.FollowerNotExistException;
import com.vijay.LinkedIn.repository.CompanyRepository;
import com.vijay.LinkedIn.repository.FollowerRepository;
import com.vijay.LinkedIn.repository.UserRepository;
import com.vijay.LinkedIn.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService{
	
	@Autowired
	private CompanyRepository companyRepository; 
	
	@Autowired
	private UserRepository userRepository; 
	
	@Autowired
	private FollowerRepository followerRepository; 
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private FollowerMapper followerMapper;

	@Override
	public ResponseEntity<CompanyDto> createCompany(CompanyRequestDto companyRequestDto) {
		CompanyEntity company = modelMapper.map(companyRequestDto, CompanyEntity.class);
		Optional<CompanyEntity> optionalCompany = companyRepository.findByEmail(company.getEmail());
		if(optionalCompany.isPresent()) {
			throw new RuntimeException("company already exist with: "+company.getEmail());
		}
		String profileUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("companies/"+company.getEmail())
				.toUriString();
		company.setProfileUrl(profileUrl);
		return new ResponseEntity<>(
				modelMapper.map(companyRepository.save(company), CompanyDto.class),
				HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<CompanyDto> retrieveCompany(String email) {
		Optional<CompanyEntity> optionalCompany = companyRepository.findByEmail(email);
		if(!optionalCompany.isPresent()) {
			throw new RuntimeException("company not exist with: "+email);
		}
		return new ResponseEntity<>(
				modelMapper.map(optionalCompany.get(), CompanyDto.class),
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<CompanyDto> updateCompany(MultipartFile profile, 
			String email, HttpServletRequest request) {
		Optional<CompanyEntity> optionalCompany = companyRepository.findByEmail(email);
		if(!optionalCompany.isPresent()) {
			throw new RuntimeException("company not exist with: "+email);
		}
		CompanyEntity company = optionalCompany.get();
		company.setAbout(request.getParameter("about"));
		String contentType = profile.getContentType();
		if(!contentType.startsWith("image/")) {
			throw new RuntimeException("it's not an image");
		}
		try {
			company.setProfile(profile.getBytes());
//			company.setCover(cover.getBytes());
		} catch (IOException e) {
			throw new RuntimeException("problem with updating image");
		}
		String profileImageUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("companies/"+company.getEmail()+"/profile")
				.toUriString();
		company.setProfileImageUrl(profileImageUrl);
		return new ResponseEntity<>(
				modelMapper.map(companyRepository.save(company), CompanyDto.class),
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<byte[]> checkProfile(String email, HttpServletRequest request) {
		CompanyEntity company = companyRepository.findByEmail(email).get();
		String mimeType = "image/jpeg";
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(mimeType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName="+"profile") 
				.body(company.getProfile());
	}

	@Override
	public ResponseEntity<FollowerDto> createFollower(
			String companyEmail, String userEmail) {
		Optional<FollowerEntity> optinalFollower = 
				followerRepository.findByUserEmailAndCompanyEmail(userEmail, companyEmail);
		if(optinalFollower.isPresent()) {
			throw new FollowerAlreadyExistException("you already follow this company");
		}
		FollowerEntity follower = new FollowerEntity();
		CompanyEntity company = companyRepository.findByEmail(companyEmail).get();
		UserEntity user = userRepository.findByEmail(userEmail).get();
		follower.setCompany(company);
		follower.setUser(user);
		followerRepository.save(follower);
		return new ResponseEntity<>(
				modelMapper.map(followerRepository.save(follower), FollowerDto.class),
				HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<String> removeFollower(String companyEmail, String userEmail) {
		FollowerEntity follower = 
				followerRepository.findByUserEmailAndCompanyEmail(userEmail, companyEmail).get();
		followerRepository.deleteById(follower.getId());
		return new ResponseEntity<>("company unfollowed",HttpStatus.OK);
	}

	@Override
	public ResponseEntity<FollowerDto> retrieveFollower(String companyEmail, String userEmail) {
		Optional<FollowerEntity> optionalFollower = 
				followerRepository.findByUserEmailAndCompanyEmail(userEmail, companyEmail);
		if(!optionalFollower.isPresent()) {
			throw new FollowerNotExistException("user not follow this company");
		}
		return new ResponseEntity<>(
				modelMapper.map(optionalFollower.get(), FollowerDto.class),
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<FollowerDto>> retrieveAllFollower(String companyEmail) {
		CompanyEntity company = companyRepository.findByEmail(companyEmail).get();
		List<FollowerDto> followerDtos = followerMapper.toFollowerDtos(company.getFollowers());
		return new ResponseEntity<>(followerDtos,HttpStatus.OK);
	}

}
