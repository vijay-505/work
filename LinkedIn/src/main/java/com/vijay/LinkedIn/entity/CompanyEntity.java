package com.vijay.LinkedIn.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
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
@Table(name="company")
public class CompanyEntity {
	
	@Id
	@GeneratedValue
	private int id;
	private String email;
	private String password;
	private String name;
	private String profileUrl;
	private String profileImageUrl;
	@Lob
	private byte[] profile;
	private String about;
	
	@OneToMany(mappedBy="company")
	private List<JobEntity> jobs;
	
	@OneToMany(mappedBy="company")
	private List<FollowerEntity> followers;
	
	@OneToMany(mappedBy="company")
	private List<PostEntity> posts;

}
