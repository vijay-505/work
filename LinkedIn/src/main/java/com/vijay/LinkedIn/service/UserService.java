package com.vijay.LinkedIn.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.vijay.LinkedIn.dto.model.UserDto;
import com.vijay.LinkedIn.dto.model.UserRequestDto;

public interface UserService {

	ResponseEntity<UserDto> createUser(UserRequestDto userRequestDto);

	ResponseEntity<UserDto> retrieveUser(String email);

	ResponseEntity<UserDto> updateUser(MultipartFile profile, String email, HttpServletRequest request);

	ResponseEntity<byte[]> checkProfile(String email, HttpServletRequest request);

}
