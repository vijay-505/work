package com.vijay.LinkedIn.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name="job")
public class JobEntity {

	@Id
	@GeneratedValue
	private int jobId;
	private String title;
	private String location;
	private Date date;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private CompanyEntity company;
	
	@Enumerated(EnumType.STRING)
	private JobType type;
	
	private String description;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private UserEntity user;
	
	@OneToMany(mappedBy="job",cascade=CascadeType.REMOVE)
	private List<AppliedJobEntity> appliedJobs;
	
	@OneToMany(mappedBy="job",cascade=CascadeType.REMOVE)
	private List<SavedJobEntity> savedJobs;
	
}

