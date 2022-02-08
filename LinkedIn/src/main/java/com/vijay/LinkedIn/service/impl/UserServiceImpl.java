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

import com.vijay.LinkedIn.dto.mapper.ExperienceMapper;
import com.vijay.LinkedIn.dto.model.ExperienceDto;
import com.vijay.LinkedIn.dto.model.UserDto;
import com.vijay.LinkedIn.dto.model.UserRequestDto;
import com.vijay.LinkedIn.entity.ExperienceEntity;
import com.vijay.LinkedIn.entity.UserEntity;
import com.vijay.LinkedIn.exception.FileErrorException;
import com.vijay.LinkedIn.exception.FileIOException;
import com.vijay.LinkedIn.exception.PermissionDeniedException;
import com.vijay.LinkedIn.exception.UserAlreadyExistsException;
import com.vijay.LinkedIn.exception.UserNotFoundException;
import com.vijay.LinkedIn.repository.ExperienceRepository;
import com.vijay.LinkedIn.repository.UserRepository;
import com.vijay.LinkedIn.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ExperienceRepository experienceRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ExperienceMapper experienceMapper;


	@Override
	public ResponseEntity<UserDto> createUser(UserRequestDto userRequestDto) {
		UserEntity user = modelMapper.map(userRequestDto, UserEntity.class);
		Optional<UserEntity> optionalUser = userRepository.findByEmail(user.getEmail());
		if(optionalUser.isPresent()) {
			throw new UserAlreadyExistsException("user already exist with: "+user.getEmail());
		}
		String profileUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("users/"+user.getEmail())
				.toUriString();
		user.setProfileUrl(profileUrl);
		return new ResponseEntity<>(
				modelMapper.map(userRepository.save(user), UserDto.class), 
				HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<UserDto> retrieveUser(String email) {
		Optional<UserEntity> optionalUser = userRepository.findByEmail(email);
		if(!optionalUser.isPresent()) {
			throw new UserNotFoundException("user not exist with: "+email);
		}
		UserEntity user = optionalUser.get();
		user.getExperiences().forEach(experience -> {
			if(experience.isPresent()) {
				experience.setEndDate(new Date());
				String experienceTimePeriod = experienceMapper.getExperienceTimePeriod(
						experience.getEndDate(),experience.getStartDate());
				experience.setTimePeriod(experienceTimePeriod);
				experienceRepository.save(experience);
			}
		});
		user.setExperiences(
				experienceMapper.toSortListOfExperienceEntity(user.getExperiences()));
		return new ResponseEntity<>(
				modelMapper.map(user, UserDto.class), 
				HttpStatus.OK);
	}


	@Override
	public ResponseEntity<UserDto> updateUser(
			UserRequestDto userRequestDto, String email) {
		UserEntity user = userRepository.findByEmail(email).get();
		if(!user.getEmail().equals(email)) {
			throw new PermissionDeniedException("you can't update user");
		}
		user.setPassword(userRequestDto.getPassword());
		user.setName(userRequestDto.getName());
		user.setAbout(userRequestDto.getAbout());
		return new ResponseEntity<>(
				modelMapper.map(userRepository.save(user), UserDto.class), 
				HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<UserDto> updateUserProfile(MultipartFile profile, String email) {
		UserEntity user = userRepository.findByEmail(email).get();
		if(!user.getEmail().equals(email)) {
			throw new PermissionDeniedException("you can't update user profile");
		}
		
		String contentType = profile.getContentType();
		if(!contentType.startsWith("image/")) {
			throw new FileErrorException("it's not an image");
		}
		try {
			user.setProfile(profile.getBytes());
		} catch (IOException e) {
			throw new FileIOException("problem with updating image");
		}
		String profileImageUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("users/"+user.getEmail()+"/")
				.path("profile")
				.toUriString();
		user.setProfileImageUrl(profileImageUrl);
		return new ResponseEntity<>(
				modelMapper.map(userRepository.save(user), UserDto.class), 
				HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<byte[]> checkProfile(String email, HttpServletRequest request) {
		UserEntity user = userRepository.findByEmail(email).get();
		String mimeType = "image/jpeg";
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(mimeType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName="+"profile") 
				.body(user.getProfile());
	}

	@Override
	public ResponseEntity<ExperienceDto> createExperience(
			@Valid ExperienceDto experienceDto,String email) {
		ExperienceEntity experience = 
				modelMapper.map(experienceDto, ExperienceEntity.class);
		UserEntity user = userRepository.findByEmail(email).get();
		experience.setUser(user);
		if(experienceDto.isPresent()) {
			experience.setEndDate(new Date());
		}
		String experienceTimePeriod = experienceMapper.getExperienceTimePeriod(
				experience.getEndDate(),experience.getStartDate());
		experience.setTimePeriod(experienceTimePeriod);
		
		return new ResponseEntity<>(
				modelMapper.map(experienceRepository.save(experience), ExperienceDto.class), 
				HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<ExperienceDto> updateExperience(@Valid ExperienceDto experienceDto, 
			String email, int experienceId) {
		ExperienceEntity experience = experienceRepository.getById(experienceId);
		UserEntity user = experience.getUser();
		if(!user.getEmail().equals(email)) {
			throw new PermissionDeniedException("you can't update experience");
		}
		ExperienceEntity experienceEntity = 
				modelMapper.map(experienceDto, ExperienceEntity.class);
		experienceEntity.setId(experienceId);
		experienceEntity.setUser(user);
		
		if(experienceDto.isPresent()) {
			experience.setEndDate(new Date());
		}
		String experienceTimePeriod = experienceMapper.getExperienceTimePeriod(
				experienceEntity.getEndDate(),experienceEntity.getStartDate());
		experienceEntity.setTimePeriod(experienceTimePeriod);
		
		return new ResponseEntity<>(
				modelMapper.map(experienceRepository.save(experienceEntity), ExperienceDto.class), 
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> deleteExperience(String email, int experienceId) {
		ExperienceEntity experience = experienceRepository.getById(experienceId);
		if(!experience.getUser().getEmail().equals(email)) {
			throw new PermissionDeniedException("you can't delete experience");
		}
		experienceRepository.deleteById(experienceId);
		return new ResponseEntity<>("experience deleted",HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<ExperienceDto>> retrieveUserExperience(String email) {
		UserEntity user = userRepository.findByEmail(email).get();
		user.getExperiences().forEach(experience -> {
			if(experience.isPresent()) {
				experience.setEndDate(new Date());
				String experienceTimePeriod = experienceMapper.getExperienceTimePeriod(
						experience.getEndDate(),experience.getStartDate());
				experience.setTimePeriod(experienceTimePeriod);
				experienceRepository.save(experience);
			}
		});
		List<ExperienceDto> experienceDtos = experienceMapper.toExperienceDtos(user.getExperiences());
		return new ResponseEntity<>(experienceDtos,HttpStatus.OK);
	}

}
