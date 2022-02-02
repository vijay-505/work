package com.vijay.LinkedIn.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vijay.LinkedIn.dto.mapper.PostMapper;
import com.vijay.LinkedIn.dto.model.PostDto;
import com.vijay.LinkedIn.dto.model.PostRequestDto;
import com.vijay.LinkedIn.entity.LinkEntity;
import com.vijay.LinkedIn.entity.PostEntity;
import com.vijay.LinkedIn.exception.PermissionDeniedException;
import com.vijay.LinkedIn.repository.LinkRepository;
import com.vijay.LinkedIn.repository.PostRepository;
import com.vijay.LinkedIn.repository.UserRepository;
import com.vijay.LinkedIn.service.PostService;

@Service
public class PostServiceImpl implements PostService{
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private LinkRepository linkRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PostMapper postMapper;

	@Override
	public ResponseEntity<PostDto> createPost(PostRequestDto postRequestDto, String email) {
		PostEntity post = modelMapper.map(postRequestDto, PostEntity.class);
		post.setUser(userRepository.findByEmail(email).get());
		post.setCreatedDate(new Date());
		postRepository.save(post);

		String likesUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("posts/"+post.getPostId()+"/likes")
				.toUriString();
		post.setLikesUrl(likesUrl);
		String commentUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("posts/"+post.getPostId()+"/comments")
				.toUriString();
		post.setCommentsUrl(commentUrl);
		List<LinkEntity> links = post.getLinks();
		for(String user: postRequestDto.getUsers()) {
			String profileUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
					.path("users/"+user)
					.toUriString();
			LinkEntity link = new LinkEntity();
			link.setLink(profileUrl);
			link.setPost(post);
			linkRepository.save(link);
			links.add(link);
		}
		post.setLinks(links);
		return new ResponseEntity<>(
				modelMapper.map(post, PostDto.class),HttpStatus.CREATED);
	}
	
	@Override
	public ResponseEntity<List<PostDto>> retrieveALLPost(String email) {
		return new ResponseEntity<>(
				postMapper.toPostDtos(userRepository.findByEmail(email).get().getPosts())
				,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<PostDto> retrievePost(int postId) {
		PostEntity post = postRepository.getById(postId);
		return new ResponseEntity<>(
				modelMapper.map(post, PostDto.class),HttpStatus.OK);
	}

	@Override
	public ResponseEntity<PostDto> updatePost(PostRequestDto postRequestDto, String email, int postId) {
		PostEntity post = postRepository.getById(postId);
		if(!post.getUser().getEmail().equals(email)) {
			throw new PermissionDeniedException("You can't update post.");
		}
		post.setDescription(postRequestDto.getDescription());
		post.setUpdatedDate(new Date());
		linkRepository.deleteAll(post.getLinks());
		post.setLinks(null);
		List<LinkEntity> links = new ArrayList<>();
		for(String user: postRequestDto.getUsers()) {
			String profileUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
					.path("users/"+user)
					.toUriString();
			LinkEntity link = new LinkEntity();
			link.setLink(profileUrl);
			link.setPost(post);
			linkRepository.save(link);
			links.add(link);
		}
		post.setLinks(links);
		postRepository.save(post);
		return new ResponseEntity<>(
				modelMapper.map(post, PostDto.class),HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> deletePost(String email, int postId) {
		PostEntity post = postRepository.getById(postId);
		if(!post.getUser().getEmail().equals(email)) {
			throw new PermissionDeniedException("You can't delete post.");
		}
		postRepository.deleteById(postId);
		return new ResponseEntity<>("post deleted successfully",HttpStatus.OK);
	}

}
