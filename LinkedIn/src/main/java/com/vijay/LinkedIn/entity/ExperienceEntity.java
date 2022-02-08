package com.vijay.LinkedIn.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="experience")
public class ExperienceEntity {
	
	@Id
	@GeneratedValue
	private int id;
	private String companyName;//location
	private String title;
	@Enumerated(EnumType.STRING)
	private JobType type;
	private Date startDate;
	private Date endDate;
	private String timePeriod;
	private boolean present;
	@ManyToOne(fetch=FetchType.LAZY)
	private UserEntity user;

}
