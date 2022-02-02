package com.vijay.LinkedIn.service.impl;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vijay.LinkedIn.dto.mapper.CommentMapper;
import com.vijay.LinkedIn.dto.model.CommentDto;
import com.vijay.LinkedIn.entity.CommentEntity;
import com.vijay.LinkedIn.entity.PostEntity;
import com.vijay.LinkedIn.exception.PermissionDeniedException;
import com.vijay.LinkedIn.repository.CommentRepository;
import com.vijay.LinkedIn.repository.PostRepository;
import com.vijay.LinkedIn.repository.UserRepository;
import com.vijay.LinkedIn.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CommentMapper commentMapper;

	@Override
	public ResponseEntity<CommentDto> createComment(
			@Valid CommentDto commentDto, int postId, String email) {
		CommentEntity comment = modelMapper.map(commentDto, CommentEntity.class);
		PostEntity post = postRepository.getById(postId);
		comment.setDate(new Date());
		comment.setPost(post);
		comment.setUser(userRepository.findByEmail(email).get());
		post.setTotalComments(post.getComments().size()+1L);
		commentRepository.save(comment);
		return new ResponseEntity<>(
				modelMapper.map(comment, CommentDto.class), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<CommentDto> updateComment(@Valid CommentDto commentDto, 
			int postId, String email, int commentId) {
		CommentEntity comment = commentRepository.getById(commentId);
		if(!comment.getUser().getEmail().equals(email)) {
			throw new PermissionDeniedException("you can't update comment");
		}
		comment.setContent(commentDto.getContent());
		comment.setDate(new Date());
		commentRepository.save(comment);
		return new ResponseEntity<>(
				modelMapper.map(comment, CommentDto.class), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> deleteComment(
			int postId, String email, int commentId) {
		CommentEntity comment = commentRepository.getById(commentId);
		PostEntity post = postRepository.getById(postId);
		if(!(comment.getUser().getEmail().equals(email) || 
				post.getUser().getEmail().equals(email))) {
			throw new PermissionDeniedException("you can't delete comment");
		}
		post.setTotalComments(post.getComments().size()-1L);
		commentRepository.deleteById(commentId);
		return new ResponseEntity<>("comment deleted successfully",HttpStatus.OK);
	}

	@Override
	public ResponseEntity<CommentDto> retrieveComment(
			int postId, int commentId) {
		return new ResponseEntity<>(
				modelMapper.map(commentRepository.getById(commentId), CommentDto.class),
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<CommentDto>> retrieveAllCommentFromPost(int postId) {
		List<CommentDto> commentDtos = commentMapper.toCommentDtos(
				postRepository.getById(postId).getComments());
		return new ResponseEntity<>(commentDtos, HttpStatus.OK);
	}

}
