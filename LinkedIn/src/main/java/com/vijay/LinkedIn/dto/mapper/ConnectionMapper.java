package com.vijay.LinkedIn.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.vijay.LinkedIn.dto.model.ConnectionDto;
import com.vijay.LinkedIn.entity.ConnectionEntity;

@Component
public class ConnectionMapper {
	
	public List<ConnectionDto> toConnectionDtos(List<ConnectionEntity> connections){
		List<ConnectionDto> connectionDtos = connections.stream().map(connection -> 
				new ModelMapper().map(connection, ConnectionDto.class))
					.collect(Collectors.toList());
		return connectionDtos;
	}

}
