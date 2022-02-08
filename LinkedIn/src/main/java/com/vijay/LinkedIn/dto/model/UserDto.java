package com.vijay.LinkedIn.dto.model;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

	private int id;
	private String email;
	private String name;
	private String about;
	private String profileUrl;
	private String profileImageUrl;
	private Long totalConnections;
	private List<ExperienceDto> experiences;
//	private List<ConnectionDto> connections;
//	private byte[] cover; 
	
}
