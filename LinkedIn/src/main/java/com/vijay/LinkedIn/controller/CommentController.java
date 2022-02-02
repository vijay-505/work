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

import com.vijay.LinkedIn.dto.model.CommentDto;
import com.vijay.LinkedIn.entity.CommentEntity;
import com.vijay.LinkedIn.repository.CommentRepository;
import com.vijay.LinkedIn.service.CommentService;

@RestController
public class CommentController {
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private CommentService commentService;
	
	@PostMapping("posts/{postId}/{email}/comments")
	public ResponseEntity<CommentDto> createComment(
			@Valid @RequestBody CommentDto commentDto,
			@PathVariable int postId,
			@PathVariable String email){
		return commentService.createComment(commentDto, postId, email);
	}
	
	@PutMapping("posts/{postId}/{email}/comments/{commentId}")
	public ResponseEntity<CommentDto> updateComment(
			@Valid @RequestBody CommentDto commentDto,
			@PathVariable int postId,
			@PathVariable String email,
			@PathVariable int commentId){
		return commentService.updateComment(commentDto, postId, email, commentId);
	}
	
	@DeleteMapping("posts/{postId}/{email}/comments/{commentId}")
	public ResponseEntity<String> deleteComment(
			@PathVariable int postId,
			@PathVariable String email,
			@PathVariable int commentId){
		return commentService.deleteComment(postId, email, commentId);
	}
	
	//get request email not require
	@GetMapping("posts/{postId}/comments/{commentId}")
	public ResponseEntity<CommentDto> retrieveComment(
			@PathVariable int postId,
			@PathVariable int commentId){
		return commentService.retrieveComment(postId, commentId);
	}
	
	@GetMapping("posts/{postId}/comments")
	public ResponseEntity<List<CommentDto>> retrieveAllCommentFromPost(
			@PathVariable int postId){
		return commentService.retrieveAllCommentFromPost(postId);
	}

}
