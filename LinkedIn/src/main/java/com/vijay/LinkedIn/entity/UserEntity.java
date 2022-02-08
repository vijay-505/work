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
@Table(name="user")
public class UserEntity {
	
	@Id
	@GeneratedValue
	private int id;
	private String email;
	private String password;
	private String name;
	private String about;
	private String profileUrl;
	private String profileImageUrl;
	@Lob
	private byte[] profile;
	@OneToMany(mappedBy="user")
	private List<ConnectionEntity> connections;
	private long totalConnections;
	
	@OneToMany(mappedBy="user")
	private List<PostEntity> posts;
	
	@OneToMany(mappedBy="user")
	private List<CommentEntity> comments;
	
	@OneToMany(mappedBy="user")
	private List<LikeEntity> likes;
	
	@OneToMany(mappedBy="user")
	private List<JobEntity> jobs;
	
	@OneToMany(mappedBy="user")
	private List<AppliedJobEntity> appliedJobs;
	
	@OneToMany(mappedBy="user")
	private List<SavedJobEntity> savedJobs;
	
	@OneToMany(mappedBy="user")
	private List<ExperienceEntity> experiences;
	
	@OneToMany(mappedBy="user")
	private List<FollowerEntity> followers;
	
//	@Lob
//	private byte[] cover; 
	//company

}
