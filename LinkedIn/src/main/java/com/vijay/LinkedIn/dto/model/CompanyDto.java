package com.vijay.LinkedIn.dto.model;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CompanyDto {
	
	private int id;
	@NotNull
	private String email;
	@NotNull
	private String name;
	private String profileUrl;
	private String profileImageUrl;
	private String about;

}
