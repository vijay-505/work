package com.vijay.LinkedIn.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vijay.LinkedIn.dto.model.LikeDto;
import com.vijay.LinkedIn.service.LikeService;

@RestController
public class LikeController {
	
	@Autowired
	private LikeService likeService;
	
	@PostMapping("/posts/{postId}/likes/users/{email}")
	public ResponseEntity<String> createLike(
			@Valid @RequestBody LikeDto likeDto,
			@PathVariable int postId,
			@PathVariable String email){
		return likeService.createLike(likeDto, postId, email);
	}
	
	@PutMapping("/posts/{postId}/likes/{likeId}/users/{email}")
	public ResponseEntity<String> updateLike(
			@Valid @RequestBody LikeDto likeDto,
			@PathVariable int postId,
			@PathVariable String email,
			@PathVariable int likeId){
		return likeService.updateLike(likeDto, postId, email, likeId);
	}
	
	@GetMapping("/posts/{postId}/likes/{likeId}")
	public ResponseEntity<LikeDto> retrieveLike(
			@PathVariable int postId,
			@PathVariable int likeId){
		return likeService.retrieveLike(postId, likeId);
	}
	
	@GetMapping("/posts/{postId}/likes")
	public ResponseEntity<List<LikeDto>> retrieveAllLikeFromPost(
			@PathVariable int postId){
		return likeService.retrieveAllLikeFromPost(postId);
	}

}
