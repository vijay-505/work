package com.vijay.LinkedIn.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
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
	//employees(people), locations, posts,jobs

}
