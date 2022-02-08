package com.vijay.LinkedIn.dto.model;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.vijay.LinkedIn.entity.JobType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ExperienceDto {
	
	private int id;
	@NotNull
	private String companyName;
	@NotNull
	private String title;
	private JobType type;
	@NotNull
	private Date startDate;
	@NotNull
	private Date endDate;
	private String timePeriod;
	@NotNull
	private boolean present;
	
}
