package com.vijay.LinkedIn.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.vijay.LinkedIn.dto.model.UserDto;
import com.vijay.LinkedIn.entity.UserEntity;

@Component
public class UserMapper {
	
	public List<UserDto> toUserDtos(List<UserEntity> users){
		List<UserDto> userDtos = users.stream().map(user -> 
				new ModelMapper().map(user, UserDto.class))
					.collect(Collectors.toList());
		return userDtos;
	}

}
