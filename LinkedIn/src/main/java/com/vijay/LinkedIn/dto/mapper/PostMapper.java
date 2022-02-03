package com.vijay.LinkedIn.dto.mapper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vijay.LinkedIn.dto.model.ActivityDto;
import com.vijay.LinkedIn.dto.model.CommentDto;
import com.vijay.LinkedIn.dto.model.LikeDto;
import com.vijay.LinkedIn.dto.model.PostDto;
import com.vijay.LinkedIn.entity.CommentEntity;
import com.vijay.LinkedIn.entity.LikeEntity;
import com.vijay.LinkedIn.entity.PostEntity;

@Component
public class PostMapper {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public List<PostDto> toPostDtos(List<PostEntity> posts){
		List<PostDto> postDtos = posts.stream().map(post -> 
			modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		return postDtos;
	}
	
	public List<ActivityDto> toActivityDtosFromPosts(List<PostEntity> posts, String reason, String email) {
		List<ActivityDto> activityDtos = new ArrayList<>();
		List<PostDto> postDtos = toPostDtos(posts);
		postDtos.forEach(postDto -> {
			ActivityDto activityDto = new ActivityDto();
			activityDto.setPostDto(postDto);
			activityDto.setReason(reason);
			activityDto.setEmail(email);
			activityDtos.add(activityDto);
		});
		return activityDtos;
	}

	public List<ActivityDto> toActivityDtosFromComments(List<CommentEntity> comments, String reason, String email) {
		List<ActivityDto> activityDtos = new ArrayList<>();
		comments.forEach(comment -> {
			ActivityDto activityDto = new ActivityDto();
			CommentDto commentDto = modelMapper.map(comment, CommentDto.class);
			PostDto postDto = modelMapper.map(comment.getPost(), PostDto.class);
			activityDto.setPostDto(postDto);
			activityDto.setCommentDto(commentDto);
			activityDto.setReason(reason);
			activityDto.setEmail(email);
			activityDtos.add(activityDto);
		});
		return activityDtos;
	}
	
	public List<ActivityDto> toActivityDtosFromLikes(List<LikeEntity> likes, String reason, String email) {
		List<ActivityDto> activityDtos = new ArrayList<>();
		likes.forEach(like -> {
			ActivityDto activityDto = new ActivityDto();
			LikeDto likeDto = modelMapper.map(like, LikeDto.class);
			PostDto postDto = modelMapper.map(like.getPost(), PostDto.class);
			activityDto.setPostDto(postDto);
			activityDto.setLikeDto(likeDto);
			activityDto.setReason(reason);
			activityDto.setEmail(email);
			activityDtos.add(activityDto);
		});
		return activityDtos;
	}

	public List<ActivityDto> sortedActivityDtos(List<ActivityDto> activityDtos) {
		List<ActivityDto> sortedActivityDtos = new ArrayList<>();
		Map<ActivityDto, Date> map = new HashMap<>();
		activityDtos.forEach(activityDto -> {
			
			if(activityDto.getReason().equals("posted")) {
				map.put(activityDto,
						activityDto.getPostDto().getCreatedDate());
			}
			else if(activityDto.getReason().equals("commented")) {
				map.put(activityDto,
						activityDto.getCommentDto().getDate());
			}
			else if(activityDto.getReason().equals("liked")) {
				map.put(activityDto,
						activityDto.getLikeDto().getDate());
			}
			
		});
		Set<Integer> set = new HashSet<>();
		map.entrySet().stream()
			.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
			.forEachOrdered(entry -> {
				ActivityDto activityDto = entry.getKey();
				if(set.add(activityDto.getPostDto().getPostId())) {
					sortedActivityDtos.add(activityDto);
				}
			});
		return sortedActivityDtos;
	}
}
