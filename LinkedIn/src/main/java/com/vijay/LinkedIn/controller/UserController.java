package com.vijay.LinkedIn.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vijay.LinkedIn.dto.model.ConnectionDto;
import com.vijay.LinkedIn.dto.model.UserDto;
import com.vijay.LinkedIn.dto.model.UserRequestDto;
import com.vijay.LinkedIn.entity.UserEntity;
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
	
	@PutMapping("/users")
	public ResponseEntity<UserDto> updateUser(@RequestParam MultipartFile profile,
			HttpServletRequest request){
		return userService.updateUser(profile,request);
	}
	
	@GetMapping("/users/{email}/profile")
	public ResponseEntity<byte[]> checkProfile(@PathVariable String email,
			HttpServletRequest request) {
		return userService.checkProfile(email,request);
	}
	
	@GetMapping("/users/{email}/connections")
	public  ResponseEntity<List<ConnectionDto>> retrieveAllConnection(
			@PathVariable String email){
		return userService.retrieveAllConnection(email);
	}

}
