package com.vijay.LinkedIn.dto.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostRequestDto {
	
	private String description;
	private List<String> users;
//	private List<String> company;

}
