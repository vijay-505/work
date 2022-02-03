package com.vijay.LinkedIn.entity;

import java.util.ArrayList;
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
	private List<ConnectionEntity> connections = new ArrayList<>();
	private long totalConnections;
	
	@OneToMany(mappedBy="user")
	private List<PostEntity> posts = new ArrayList<>();
	
	@OneToMany(mappedBy="user")
	private List<CommentEntity> comments = new ArrayList<>();
	
	@OneToMany(mappedBy="user")
	private List<LikeEntity> likes = new ArrayList<>();
	
//	@Lob
//	private byte[] cover; 
	//job,company, posts, connections

}
