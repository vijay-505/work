package com.vijay.LinkedIn.dto.model;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JobDto {
	
	private int jobId;
	@NotNull
	private String title;
	@NotNull
	private String location;
	private Date date;
	@Email
	@NotNull
	private String companyEmail;
	private String companyProfileUrl;
	@NotNull
	private String jobType;
	@NotNull
	private String description;
	@Email
	private String userEmail;
	private String userProfileUrl;
	

}
