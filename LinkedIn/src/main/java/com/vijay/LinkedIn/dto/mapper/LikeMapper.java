package com.vijay.LinkedIn.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.vijay.LinkedIn.dto.model.LikeDto;
import com.vijay.LinkedIn.entity.LikeEntity;

@Component
public class LikeMapper {
	
	public List<LikeDto> toLikeDtos(List<LikeEntity> likes){
		List<LikeDto> likeDtos = likes.stream().map(like -> 
				new ModelMapper().map(like, LikeDto.class))
				.collect(Collectors.toList());
		return likeDtos;
	}

}
