package com.vijay.LinkedIn.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import com.vijay.LinkedIn.dto.model.LikeDto;

public interface LikeService {

	ResponseEntity<String> createLike(@Valid LikeDto likeDto, 
			int postId, String email);

	ResponseEntity<String> updateLike(@Valid LikeDto likeDto, 
			int postId, String email, int likeId);

	ResponseEntity<LikeDto> retrieveLike(int postId, int likeId);

	ResponseEntity<List<LikeDto>> retrieveAllLikeFromPost(int postId);

}
