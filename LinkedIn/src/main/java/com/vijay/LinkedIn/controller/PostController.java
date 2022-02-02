package com.vijay.LinkedIn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vijay.LinkedIn.dto.model.PostDto;
import com.vijay.LinkedIn.dto.model.PostRequestDto;
import com.vijay.LinkedIn.service.PostService;

@RestController
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@PostMapping("/users/{email}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostRequestDto postRequestDto,
			@PathVariable String email){
		return postService.createPost(postRequestDto, email);
	}
	
	@GetMapping("/users/{email}/posts")
	public ResponseEntity<List<PostDto>> retrieveAllPost(@PathVariable String email){
		return postService.retrieveALLPost(email);
	}
	
	@GetMapping("/users/{email}/posts/{postId}")
	public ResponseEntity<PostDto> retrievePost(@PathVariable String email,
			@PathVariable int postId){
		return postService.retrievePost(email, postId);
	}
	
	@PutMapping("/users/{email}/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(
			@RequestBody PostRequestDto postRequestDto,
			@PathVariable String email,
			@PathVariable int postId){
		return postService.updatePost(postRequestDto, email, postId);
	}
	
	@DeleteMapping("/users/{email}/posts/{postId}")
	public ResponseEntity<String> deletePost(@PathVariable String email,
			@PathVariable int postId){
		return postService.deletePost(email, postId);
	}

}
