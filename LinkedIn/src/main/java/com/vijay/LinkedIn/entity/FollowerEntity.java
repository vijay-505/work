package com.vijay.LinkedIn.entity;

import javax.persistence.Entity;
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
@Table(name="follower")
public class FollowerEntity {
	
	@Id
	@GeneratedValue
	private int id;
	@ManyToOne(fetch=FetchType.LAZY)
	private UserEntity user;
	@ManyToOne(fetch=FetchType.LAZY)
	private CompanyEntity company;

}
