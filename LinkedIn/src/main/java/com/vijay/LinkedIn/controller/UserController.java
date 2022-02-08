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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vijay.LinkedIn.dto.model.ExperienceDto;
import com.vijay.LinkedIn.dto.model.UserDto;
import com.vijay.LinkedIn.dto.model.UserRequestDto;
import com.vijay.LinkedIn.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/users")
	public  ResponseEntity<UserDto> createUser(@Valid @RequestBody UserRequestDto userRequestDto){
		return userService.createUser(userRequestDto);
	}
	
	@GetMapping("/users/{email}")
	public  ResponseEntity<UserDto> retrieveUser(@PathVariable String email){
		return userService.retrieveUser(email);
	}
	
	@PutMapping("/users/{email}")
	public ResponseEntity<UserDto> updateUser(
			@Valid @RequestBody UserRequestDto userRequestDto,
			@PathVariable String email){
		return userService.updateUser(userRequestDto, email);
	}
	
	@PutMapping("/users/{email}/profile")
	public ResponseEntity<UserDto> updateUserProfile(@RequestParam MultipartFile profile,
			@PathVariable String email){
		return userService.updateUserProfile(profile, email);
	}
	
	@GetMapping("/users/{email}/profile")
	public ResponseEntity<byte[]> checkProfile(@PathVariable String email,
			HttpServletRequest request) {
		return userService.checkProfile(email,request);
	}
	
	@PostMapping("/users/{email}/experiences")
	public  ResponseEntity<ExperienceDto> createExperience(
			@Valid @RequestBody ExperienceDto experienceDto,
			@PathVariable String email){
		return userService.createExperience(experienceDto, email);
	}
	
	@PutMapping("/users/{email}/experiences/{experienceId}")
	public  ResponseEntity<ExperienceDto> updateExperience(
			@Valid @RequestBody ExperienceDto experienceDto,
			@PathVariable String email, @PathVariable int experienceId){
		return userService.updateExperience(experienceDto, email, experienceId);
	}
	
	@DeleteMapping("/users/{email}/experiences/{experienceId}")
	public  ResponseEntity<String> deleteExperience(
			@PathVariable String email, @PathVariable int experienceId){
		return userService.deleteExperience(email, experienceId);
	}
	
	@GetMapping("/users/{email}/experiences")
	public  ResponseEntity<List<ExperienceDto>> retrieveUserExperience(
			@PathVariable String email){
		return userService.retrieveUserExperience(email);
	}

}
