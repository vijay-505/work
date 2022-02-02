package com.vijay.LinkedIn.dto.model;

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
//	private List<ConnectionDto> connections;
//	private byte[] cover; 
	
}
