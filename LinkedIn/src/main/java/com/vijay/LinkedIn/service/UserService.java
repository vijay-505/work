package com.vijay.LinkedIn.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.vijay.LinkedIn.dto.model.ExperienceDto;
import com.vijay.LinkedIn.dto.model.UserDto;
import com.vijay.LinkedIn.dto.model.UserRequestDto;

public interface UserService {

	ResponseEntity<UserDto> createUser(UserRequestDto userRequestDto);

	ResponseEntity<UserDto> retrieveUser(String email);

	ResponseEntity<UserDto> updateUser(UserRequestDto userRequestDto, String email);
	
	ResponseEntity<UserDto> updateUserProfile(MultipartFile profile, String email);

	ResponseEntity<byte[]> checkProfile(String email, HttpServletRequest request);

	ResponseEntity<ExperienceDto> createExperience(
			@Valid ExperienceDto experienceDto,String email);

	ResponseEntity<ExperienceDto> updateExperience(@Valid ExperienceDto experienceDto, 
			String email, int experienceId);

	ResponseEntity<String> deleteExperience(String email, int experienceId);

	ResponseEntity<List<ExperienceDto>> retrieveUserExperience(String email);

}
