package com.vijay.LinkedIn.dto.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AppliedJobDto {

	private int id;
	private String UserEmail;
	private String phone;
	private String resumeUrl;
	private String userProfileUrl;
}
