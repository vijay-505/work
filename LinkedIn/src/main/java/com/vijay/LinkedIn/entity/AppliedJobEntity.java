package com.vijay.LinkedIn.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
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
@Table(name="job_action")
public class AppliedJobEntity {
	
	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private UserEntity user;
	
	private String phone;
	private String resumeUrl;
	@Lob
	private byte[] resume;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private JobEntity job;

}
