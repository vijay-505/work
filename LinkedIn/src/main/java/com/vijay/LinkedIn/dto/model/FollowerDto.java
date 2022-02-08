package com.vijay.LinkedIn.dto.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FollowerDto {
	
	private int id;
	private String userEmail;
	private String userProfileUrl;
	private String companyEmail;

}
