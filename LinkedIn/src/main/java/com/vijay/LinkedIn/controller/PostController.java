package com.vijay.LinkedIn.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vijay.LinkedIn.dto.model.ActivityDto;
import com.vijay.LinkedIn.dto.model.PostDto;
import com.vijay.LinkedIn.dto.model.PostRequestDto;
import com.vijay.LinkedIn.service.PostService;

@RestController
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@PostMapping("/posts/users/{email}")
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostRequestDto postRequestDto,
			@PathVariable String email){
		return postService.createPost(postRequestDto, email);
	}
	
	@GetMapping("/posts/users/{email}")
	public ResponseEntity<List<PostDto>> retrieveAllPost(@PathVariable String email){
		return postService.retrieveALLPost(email);
	}
	
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> retrievePost(@PathVariable int postId){
		return postService.retrievePost(postId);
	}
	
	@PutMapping("/posts/{postId}/users/{email}")
	public ResponseEntity<PostDto> updatePost(
			@Valid @RequestBody PostRequestDto postRequestDto,
			@PathVariable String email,
			@PathVariable int postId){
		return postService.updatePost(postRequestDto, email, postId);
	}
	
	@DeleteMapping("/posts/{postId}/users/{email}")
	public ResponseEntity<String> deletePost(@PathVariable String email,
			@PathVariable int postId){
		return postService.deletePost(email, postId);
	}
	
	@GetMapping("/users/{email}/activity")
	public ResponseEntity<List<ActivityDto>> retrieveAllActivityPost(@PathVariable String email){
		return postService.retrieveAllActivityPost(email);
	}
	
	@GetMapping("/users/{email}/homepage")
	public ResponseEntity<List<ActivityDto>> retrieveAllActivityHomePage(@PathVariable String email){
		return postService.retrieveAllActivityHomePage(email);
	}
	
	@PostMapping("/posts/companies/{email}")
	public ResponseEntity<PostDto> createCompanyPost(@Valid @RequestBody PostRequestDto postRequestDto,
			@PathVariable String email){
		return postService.createCompanyPost(postRequestDto, email);
	}
	
	@GetMapping("/posts/companies/{email}")
	public ResponseEntity<List<PostDto>> retrieveCompanyAllPost(@PathVariable String email){
		return postService.retrieveCompanyAllPost(email);
	}
	
	@PutMapping("/posts/{postId}/companies/{email}")
	public ResponseEntity<PostDto> updateCompanyPost(
			@Valid @RequestBody PostRequestDto postRequestDto,
			@PathVariable String email,
			@PathVariable int postId){
		return postService.updateCompanyPost(postRequestDto, email, postId);
	}
	
	@DeleteMapping("/posts/{postId}/companies/{email}")
	public ResponseEntity<String> deleteCompanyPost(@PathVariable String email,
			@PathVariable int postId){
		return postService.deleteCompanyPost(email, postId);
	}
	
	@GetMapping("/companies/{email}/activity")
	public ResponseEntity<List<ActivityDto>> retrieveCompanyAllActivityPost(@PathVariable String email){
		return postService.retrieveCompanyAllActivityPost(email);
	}

}
