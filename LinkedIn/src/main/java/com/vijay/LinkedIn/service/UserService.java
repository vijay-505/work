package com.vijay.LinkedIn.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.vijay.LinkedIn.dto.model.ConnectionDto;
import com.vijay.LinkedIn.dto.model.UserDto;
import com.vijay.LinkedIn.entity.UserEntity;

public interface UserService {

	ResponseEntity<UserDto> createUser(UserEntity user);

	ResponseEntity<UserDto> retrieveUser(String email);

	ResponseEntity<UserDto> updateUser(MultipartFile profile, MultipartFile cover, HttpServletRequest request);

	ResponseEntity<byte[]> checkProfile(String email, HttpServletRequest request);

	ResponseEntity<List<ConnectionDto>> retrieveAllConnection(String email);

}
