package com.vijay.LinkedIn.dto.model;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JobResponseDto {
	
	private int jobId;
	private String title;
	private String companyEmail;
	private String location;
	private String jobType;
	private Date date;

}
