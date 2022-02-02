package com.vijay.LinkedIn.service.impl;

import java.io.IOException;
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

import com.vijay.LinkedIn.dto.model.UserDto;
import com.vijay.LinkedIn.dto.model.UserRequestDto;
import com.vijay.LinkedIn.entity.UserEntity;
import com.vijay.LinkedIn.exception.FileErrorException;
import com.vijay.LinkedIn.exception.FileIOException;
import com.vijay.LinkedIn.exception.UserAlreadyExistsException;
import com.vijay.LinkedIn.exception.UserNotFoundException;
import com.vijay.LinkedIn.repository.UserRepository;
import com.vijay.LinkedIn.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;


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
		return new ResponseEntity<>(
				modelMapper.map(optionalUser.get(), UserDto.class), 
				HttpStatus.OK);
	}


	@Override
	public ResponseEntity<UserDto> updateUser(MultipartFile profile, 
			String email, HttpServletRequest request) {
		Optional<UserEntity> optionalUser = userRepository.findByEmail(email);
		if(!optionalUser.isPresent()) {
			throw new UserNotFoundException("user not exist with: "+email);
		}
		UserEntity user = optionalUser.get();
		user.setName(request.getParameter("name"));
		user.setAbout(request.getParameter("about"));
		String contentType = profile.getContentType();
		if(!contentType.startsWith("image/")) {
			throw new FileErrorException("it's not an image");
		}
		try {
			user.setProfile(profile.getBytes());
//			user.setCover(cover.getBytes());
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

}
