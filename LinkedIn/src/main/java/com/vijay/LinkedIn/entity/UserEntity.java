package com.vijay.LinkedIn.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

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
	@NotNull
	@Email
	private String email;
	@NotNull
	private String password;
	@NotNull
	private String name;
	private String about;
	private String profileUrl;
	@Lob
	private byte[] profile;
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL)
	private List<ConnectionEntity> connections = new ArrayList<>();
	private long totalConnections;
	
//	@Lob
//	private byte[] cover; 
	//job,company, posts, connections

}
