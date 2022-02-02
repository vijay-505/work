package com.vijay.LinkedIn.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import com.vijay.LinkedIn.dto.model.CommentDto;

public interface CommentService {

	ResponseEntity<CommentDto> createComment(
			@Valid CommentDto commentDto, int postId, String email);

	ResponseEntity<CommentDto> updateComment(@Valid CommentDto commentDto, 
			int postId, String email, int commentId);

	ResponseEntity<String> deleteComment( 
			int postId, String email, int commentId);

	ResponseEntity<CommentDto> retrieveComment( 
			int postId, int commentId);

	ResponseEntity<List<CommentDto>> retrieveAllCommentFromPost(
			int postId);

}
