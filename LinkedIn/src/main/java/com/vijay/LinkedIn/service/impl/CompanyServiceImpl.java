package com.vijay.LinkedIn.service.impl;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vijay.LinkedIn.dto.model.CompanyDto;
import com.vijay.LinkedIn.entity.CompanyEntity;
import com.vijay.LinkedIn.repository.CompanyRepository;
import com.vijay.LinkedIn.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService{
	
	@Autowired
	private CompanyRepository companyRepository; 
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ResponseEntity<CompanyDto> createCompany(CompanyEntity company) {
		Optional<CompanyEntity> optionalCompany = companyRepository.findByEmail(company.getEmail());
		if(optionalCompany.isPresent()) {
			throw new RuntimeException("company already exist with: "+company.getEmail());
		}
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
	public ResponseEntity<CompanyDto> updateCompany(MultipartFile profile, HttpServletRequest request) {
		String email = request.getParameter("email");
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
		String profileUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path(""+company.getId()+"/")
				.path(profile.getOriginalFilename())
				.toUriString();
		company.setProfileUrl(profileUrl);
		return new ResponseEntity<>(
				modelMapper.map(companyRepository.save(company), CompanyDto.class),
				HttpStatus.OK);
	}

}
