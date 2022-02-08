package com.vijay.LinkedIn.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.vijay.LinkedIn.dto.model.FollowerDto;
import com.vijay.LinkedIn.entity.FollowerEntity;

@Component
public class FollowerMapper {

	public List<FollowerDto> toFollowerDtos(List<FollowerEntity> followers){
		List<FollowerDto> followerDtos = followers.stream().map(follower -> 
				new ModelMapper().map(follower, FollowerDto.class))
					.collect(Collectors.toList());
		return followerDtos;
	}
}
