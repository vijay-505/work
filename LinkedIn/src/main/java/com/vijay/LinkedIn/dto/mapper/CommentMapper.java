package com.vijay.LinkedIn.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.vijay.LinkedIn.dto.model.CommentDto;
import com.vijay.LinkedIn.entity.CommentEntity;

@Component
public class CommentMapper {
	
	public List<CommentDto> toCommentDtos(List<CommentEntity> comments){
		List<CommentDto> commentDtos = comments.stream().map(comment -> 
				new ModelMapper().map(comment, CommentDto.class))
				.collect(Collectors.toList());
		return commentDtos;
	}

}
