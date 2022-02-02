package com.vijay.LinkedIn.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.vijay.LinkedIn.dto.model.PostDto;
import com.vijay.LinkedIn.entity.PostEntity;

@Component
public class PostMapper {
	
	public List<PostDto> toPostDtos(List<PostEntity> posts){
		List<PostDto> postDtos = posts.stream().map(post -> 
				new ModelMapper().map(post, PostDto.class))
				.collect(Collectors.toList());
		return postDtos;
	}

}
