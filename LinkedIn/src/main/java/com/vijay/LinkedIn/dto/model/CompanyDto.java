package com.vijay.LinkedIn.dto.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CompanyDto {
	
	private int id;
	private String email;
	private String name;
	private String profileUrl;
	private String profileImageUrl;
	private String about;

}
