package com.vijay.LinkedIn.dto.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDto {
	
	private int id;
	private String email;
	private String name;
	private String profileUrl;
	private String about;

}
