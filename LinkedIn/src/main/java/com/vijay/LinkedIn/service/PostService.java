package com.vijay.LinkedIn.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.vijay.LinkedIn.dto.model.ActivityDto;
import com.vijay.LinkedIn.dto.model.PostDto;
import com.vijay.LinkedIn.dto.model.PostRequestDto;

public interface PostService {

	ResponseEntity<PostDto> createPost(PostRequestDto postRequestDto, String email);
	
	ResponseEntity<List<PostDto>> retrieveALLPost(String email);

	ResponseEntity<PostDto> retrievePost(int postId);

	ResponseEntity<PostDto> updatePost(PostRequestDto postRequestDto, 
			String email, int postId);

	ResponseEntity<String> deletePost(String email, int postId);

	ResponseEntity<List<ActivityDto>> retrieveAllActivityPost(String email);

	ResponseEntity<List<ActivityDto>> retrieveAllActivityHomePage(String email);

}
