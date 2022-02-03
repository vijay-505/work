package com.vijay.LinkedIn.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vijay.LinkedIn.dto.mapper.LikeMapper;
import com.vijay.LinkedIn.dto.model.LikeDto;
import com.vijay.LinkedIn.entity.LikeEntity;
import com.vijay.LinkedIn.entity.PostEntity;
import com.vijay.LinkedIn.entity.React;
import com.vijay.LinkedIn.exception.PermissionDeniedException;
import com.vijay.LinkedIn.repository.LikeRepository;
import com.vijay.LinkedIn.repository.PostRepository;
import com.vijay.LinkedIn.repository.UserRepository;
import com.vijay.LinkedIn.service.LikeService;

@Service
public class LikeServiceImpl implements LikeService{
	
	@Autowired
	private LikeRepository likeRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelmapper;
	
	@Autowired
	private LikeMapper likeMapper;

	@Override
	public ResponseEntity<String> createLike(@Valid LikeDto likeDto, 
			int postId, String email) {
		Optional<LikeEntity> optionalLike = 
				likeRepository.findByUserEmailAndPostPostId(email, postId);
		PostEntity post = postRepository.getById(postId);
		if(optionalLike.isPresent()) {
			post.setTotalLikes(post.getLikes().size()-1L);
			likeRepository.delete(optionalLike.get());
			return new ResponseEntity<>("post like removed.",HttpStatus.CREATED);
		}
		else {
			LikeEntity like = modelmapper.map(likeDto, LikeEntity.class);
			like.setPost(post);
			like.setUser(userRepository.findByEmail(email).get());
			like.setReact(React.LIKE);
			like.setDate(new Date());
			post.setTotalLikes(post.getLikes().size()+1L);
			likeRepository.save(like);
			return new ResponseEntity<>("post liked.",HttpStatus.CREATED);
		}
		
	}


	@Override
	public ResponseEntity<String> updateLike(@Valid LikeDto likeDto, 
			int postId, String email, int likeId) {
		LikeEntity like = likeRepository.getById(likeId);
		if(!like.getUser().getEmail().equals(email)) {
			throw new PermissionDeniedException("you can't update like");
		}
		like.setReact(likeDto.getReact());
		like.setDate(new Date());
		likeRepository.save(like);
		return new ResponseEntity<>("post reaction changed.",HttpStatus.OK);
	}


	@Override
	public ResponseEntity<LikeDto> retrieveLike(int postId, int likeId) {
		return new ResponseEntity<>(
				modelmapper.map(likeRepository.getById(likeId), LikeDto.class), 
				HttpStatus.OK);
	}


	@Override
	public ResponseEntity<List<LikeDto>> retrieveAllLikeFromPost(int postId) {
		List<LikeDto> likeDtos = likeMapper.toLikeDtos(
				postRepository.getById(postId).getLikes());
		return new ResponseEntity<>(likeDtos,HttpStatus.OK);
	}

}
